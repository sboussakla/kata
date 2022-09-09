package com.sg.kata.services.account;

import com.sg.kata.entities.Account;
import com.sg.kata.exceptions.AccountNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    @Transactional(readOnly = true)
    Account getAccountById(Long accountId) throws AccountNotFoundException;

    @Transactional
    Account save(Account account);
}
