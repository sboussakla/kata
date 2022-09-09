package com.sg.kata.services.transaction.history.impl;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.Transaction;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.repository.TransactionHistoryRepository;
import com.sg.kata.services.account.AccountService;
import com.sg.kata.services.transaction.TransactionService;
import com.sg.kata.services.transaction.history.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> getHistory(Long accountId) throws AccountNotFoundException {
        Account account = accountService.getAccountById(accountId);
        return transactionHistoryRepository.findAllByAccountId(accountId);

    }
}
