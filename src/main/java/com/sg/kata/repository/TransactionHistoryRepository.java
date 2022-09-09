package com.sg.kata.repository;

import com.sg.kata.entities.Account;
import com.sg.kata.entities.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>{

    List<TransactionHistory> findAllByAccountId(Long accountId);

}
