package com.sg.kata.services.transaction.impl;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Transaction;
import com.sg.kata.services.account.AccountService;
import com.sg.kata.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountService accountService;

    @Override
    public Transaction createTransaction(OperationDto operationDto) {
        return Transaction.builder()
                .transactionType(operationDto.getTransactionType())
                .transactionDate(LocalDateTime.now())
                .accountId(operationDto.getAccountId())
                .transactionAmount(operationDto.getAmount())
                .build();
    }


}
