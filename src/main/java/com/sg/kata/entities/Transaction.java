package com.sg.kata.entities;

import com.sg.kata.enums.TransactionType;
import com.sg.kata.services.account.AccountService;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime transactionDate;
    @Column
    private BigDecimal transactionAmount;
    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column
    private Long accountId;
}
