package com.sg.kata.repository;

import com.sg.kata.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{


}
