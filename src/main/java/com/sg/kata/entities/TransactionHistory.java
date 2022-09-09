package com.sg.kata.entities;

import com.sg.kata.enums.TransactionType;
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
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column
    private LocalDateTime transactionDate;
    @Column
    private BigDecimal transactionAmount;
    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column
    private BigDecimal balance;
    @Column
    private Long accountId;
}
