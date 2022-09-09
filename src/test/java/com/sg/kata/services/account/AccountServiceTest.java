package com.sg.kata.services.account;


import com.sg.kata.AbstractServiceTest;
import com.sg.kata.entities.Account;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.exceptions.BalanceException;
import com.sg.kata.util.Util;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AccountServiceTest extends AbstractServiceTest {

    @Mock
    private AccountService accountService;



    @Test
    public void test_getAccount_found() {
        // Given
        Account account = Util.buildAccount(1L);
        when(accountService.getAccountById(1l)).thenReturn(account);

        // When
        Account accountById = accountService.getAccountById(1l);

        // Then
        Assertions.assertEquals(account.getId(), accountById.getId());

    }

    @Test
    public void test_getAccount_not_found() {
        // GIVEN
        when(accountService.getAccountById(1L)).thenThrow(new AccountNotFoundException("Account not found, accountId: 1"));
        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountById(1L);
        });

        String expectedMessage = "Account not found, accountId: 1";
        String actualMessage = exception.getMessage();

        // Then

        assertTrue(actualMessage.contains(expectedMessage));

    }


}
