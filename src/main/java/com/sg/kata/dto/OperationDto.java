package com.sg.kata.dto;

import com.sg.kata.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OperationDto {
    BigDecimal amount;
    Long accountId;
    TransactionType transactionType;
}
