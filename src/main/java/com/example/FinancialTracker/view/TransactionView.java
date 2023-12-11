package com.example.FinancialTracker.view;

import com.example.FinancialTracker.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionView {

    private Long id;

    private TransactionType transactionType;

    private BigDecimal amount;

    private String date;

    private String comment;

}
