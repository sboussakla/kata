package com.sg.kata.services.transaction.history;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Transaction;
import com.sg.kata.entities.TransactionHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionHistory> getHistory(Long accountId);

}
