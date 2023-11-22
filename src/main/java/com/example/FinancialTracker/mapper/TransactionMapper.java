package com.example.FinancialTracker.mapper;

import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionMapper {

    public static TransactionEntity toEntity(TransactionForm transactionForm) {
        return TransactionEntity.builder()
                .transactionType(transactionForm.getAmount().compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(TransactionType.INCOME) : String.valueOf(TransactionType.EXPENSE))
                .amount(transactionForm.getAmount().abs())
                .date(LocalDate.parse(transactionForm.getDate()))
                .build();
    }

    public static TransactionView toView(TransactionEntity transactionEntity) {
        return TransactionView.builder()
                .transactionType(transactionEntity.getTransactionType())
                .amount(transactionEntity.getAmount())
                .date(transactionEntity.getDate().toString())
                .build();
    }

}