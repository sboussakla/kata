package com.sg.kata.services.transaction;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Transaction;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {

    @Transactional
    Transaction createTransaction(OperationDto operationDto);

}
