package com.sg.kata.services.operation;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.exceptions.BalanceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OperationService {
    @Transactional
    Account createOperation(OperationDto operationDto) throws AccountNotFoundException, BalanceException;

    List<TransactionHistory> getAccountHistory(Long accountId) throws AccountNotFoundException;
}
