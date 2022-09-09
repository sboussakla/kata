package com.sg.kata.services.transaction;


import com.sg.kata.AbstractServiceTest;
import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.Transaction;
import com.sg.kata.enums.TransactionType;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.util.Util;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TransactionServiceTest extends AbstractServiceTest {

    @Mock
    private TransactionService transactionService;



    @Test
    public void test_createTransaction_success() {
        // Given
        Transaction transaction = Util.buildTransaction(1L, TransactionType.WITHDRAWAL);
        Account account = Util.buildAccount(1L);
        OperationDto operationDto = Util.buildOperation(1L, transaction, account);
        when(transactionService.createTransaction(operationDto)).thenReturn(transaction);

        // When
        Transaction createdTransaction = transactionService.createTransaction(operationDto);

        // Then
        Assertions.assertEquals(createdTransaction.getTransactionType(), transaction.getTransactionType());

    }


}
