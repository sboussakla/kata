package com.sg.kata.services.operation.impl;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.Transaction;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.enums.TransactionType;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.exceptions.BalanceException;
import com.sg.kata.repository.TransactionHistoryRepository;
import com.sg.kata.services.account.AccountService;
import com.sg.kata.services.operation.OperationService;
import com.sg.kata.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public Account createOperation(OperationDto operationDto) throws AccountNotFoundException, BalanceException {
        Transaction transaction = transactionService.createTransaction(operationDto);
        Account account = accountService.getAccountById(operationDto.getAccountId());
        Account savedAccount = accountService.save(TransactionType.WITHDRAWAL == operationDto.getTransactionType()
                ? withdrawal(transaction, account) : deposit(transaction, account));
        saveHistory(transaction, account);
        return savedAccount;
    }

    @Override
    public List<TransactionHistory> getAccountHistory(Long accountId) throws AccountNotFoundException {
        return transactionHistoryRepository.findAllByAccountId(accountId);
    }

    private Account deposit(Transaction transaction, Account account) throws AccountNotFoundException {
        account.getTransactions().add(transaction);
        account.setBalanceAccount(account.getBalanceAccount().add(transaction.getTransactionAmount()));

        return account;
    }


    private Account withdrawal(Transaction transaction, Account account) throws AccountNotFoundException, BalanceException {
        if (transaction.getTransactionAmount().compareTo(account.getBalanceAccount()) > 0) {
            throw new BalanceException("Balance exception, accountId: "+ account.getId());
        }
        account.getTransactions().add(transaction);
        account.setBalanceAccount(account.getBalanceAccount().subtract(transaction.getTransactionAmount()));
        return account;
    }

    private void saveHistory(Transaction transaction, Account account) {
        transactionHistoryRepository.save(TransactionHistory.builder()
                .transactionType(transaction.getTransactionType())
                .transactionAmount(transaction.getTransactionAmount())
                .accountId(transaction.getAccountId())
                .transactionDate(transaction.getTransactionDate())
                .balance(account.getBalanceAccount())
                .build());
    }



}
