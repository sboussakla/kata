package com.sg.kata.services.operation;

import com.sg.kata.AbstractServiceTest;
import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.Transaction;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.enums.TransactionType;
import com.sg.kata.exceptions.BalanceException;
import com.sg.kata.services.transaction.TransactionService;
import com.sg.kata.util.Util;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

public class OperationServiceTest extends AbstractServiceTest {

    @Mock
    private OperationService operationService;

    @Test
    public void test_createOperation_deposit_success() {
        // Given

        Transaction depositTransaction = Util.buildTransaction(2L, TransactionType.DEPOSIT);
        Account account = Util.buildAccount(1L);
        OperationDto operationDto = Util.buildOperation(1L, depositTransaction, account);
        account.setTransactions(Lists.newArrayList(depositTransaction));
        Mockito.when(operationService.createOperation(operationDto)).thenReturn(account);

        // When

        Account operation = operationService.createOperation(operationDto);
        // Then
        Assertions.assertThat(operationDto.getTransactionType().equals(TransactionType.DEPOSIT));
        Assertions.assertThat(operation.getTransactions().size() == account.getTransactions().size());
        Assertions.assertThat(operation.getBalanceAccount().equals(account.getBalanceAccount()));
    }

    @Test
    public void test_createOperation_withdrawal_success() {
        // Given

        Transaction withdrawalTransaction = Util.buildTransaction(2L, TransactionType.WITHDRAWAL);
        Account account = Util.buildAccount(1L);
        OperationDto operationDto = Util.buildOperation(1L, withdrawalTransaction, account);
        account.setTransactions(Lists.newArrayList(withdrawalTransaction));
        Mockito.when(operationService.createOperation(operationDto)).thenReturn(account);

        // When

        Account operation = operationService.createOperation(operationDto);
        // Then
        Assertions.assertThat(operationDto.getTransactionType().equals(TransactionType.WITHDRAWAL));
        Assertions.assertThat(operation.getTransactions().size() == account.getTransactions().size());
        Assertions.assertThat(operation.getBalanceAccount().equals(account.getBalanceAccount()));
    }

    @Test
    public void test_createOperation_withdrawal_error() throws BalanceException{
        // Given

        Transaction withdrawalTransaction = Util.buildTransaction(2L, TransactionType.WITHDRAWAL);
        Account account = Util.buildAccount(1L);
        account.setBalanceAccount(BigDecimal.ZERO);
        OperationDto operationDto = Util.buildOperation(1L, withdrawalTransaction, account);
        account.setTransactions(Lists.newArrayList(withdrawalTransaction));
        Mockito.when(operationService.createOperation(operationDto)).thenThrow(new BalanceException("Balance exception, accountId: 1"));

        // When
        Exception exception = assertThrows(BalanceException.class, () -> {
            operationService.createOperation(operationDto);
        });

        String expectedMessage = "Balance exception, accountId: 1";
        String actualMessage = exception.getMessage();

        // Then

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_getHistory() {
        // Given

        TransactionHistory depositTransaction = Util.buildTransactionHistory(1L, TransactionType.DEPOSIT);
        TransactionHistory withdrawalTransaction = Util.buildTransactionHistory(2L, TransactionType.WITHDRAWAL);

        Mockito.when(operationService.getAccountHistory(1L)).thenReturn(Lists.newArrayList(depositTransaction, withdrawalTransaction));

        // When

        List<TransactionHistory> accountHistory = operationService.getAccountHistory(1L);
        // Then

        Assertions.assertThat(accountHistory.size() == 2);
    }

}
