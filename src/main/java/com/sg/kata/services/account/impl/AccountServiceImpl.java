package com.sg.kata.services.account.impl;

import com.sg.kata.entities.Account;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.repository.AccountRepository;
import com.sg.kata.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("Account with id " + id + " not found"));

    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
