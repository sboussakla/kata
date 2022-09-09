package com.sg.kata.util;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.Transaction;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.enums.TransactionType;
import org.assertj.core.util.Lists;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Util {

    public static Account buildAccount(Long id) {
        return Account.builder()
                .id(id)
                .balanceAccount(BigDecimal.valueOf(1000 * Math.random()))
                .transactions(Lists.newArrayList())
                .build();
    }

    public static Transaction buildTransaction(Long id, TransactionType type) {
        return Transaction.builder()
                .id(id)
                .transactionAmount(BigDecimal.valueOf(100 * Math.random()))
                .transactionDate(LocalDateTime.now())
                .transactionType(type)
                .build();
    }

    public static OperationDto buildOperation(Long id, Transaction transaction, Account account) {
        return OperationDto.builder()
                .accountId(account.getId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getTransactionAmount())
                .build();
    }

    public static TransactionHistory buildTransactionHistory(Long id, TransactionType type) {
        return TransactionHistory.builder()
                .transactionType(type)
                .transactionAmount(BigDecimal.valueOf(100 * Math.random()))
                .transactionDate(LocalDateTime.now())
                .transactionType(type)
                .build();
    }
}
