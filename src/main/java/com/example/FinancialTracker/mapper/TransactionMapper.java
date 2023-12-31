package com.example.FinancialTracker.mapper;

import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionMapper {

    public static TransactionEntity toEntity(TransactionForm transactionForm, Long categoryId, Long userId) {
        return TransactionEntity.builder()
                .transactionType(transactionForm.getAmount().compareTo(BigDecimal.ZERO) > 0 ? TransactionType.INCOME : TransactionType.EXPENSE)
                .categoryId(categoryId)
                .userId(userId)
                .amount(transactionForm.getAmount().abs())
                .date(LocalDate.parse(transactionForm.getDate()))
                .comment(transactionForm.getComment())
                .build();
    }

    public static TransactionView toView(TransactionEntity transactionEntity) {
        return TransactionView.builder()
                .transactionType(transactionEntity.getTransactionType())
                .amount(transactionEntity.getAmount())
                .date(transactionEntity.getDate().toString())
                .comment(transactionEntity.getComment())
                .build();
    }

}
