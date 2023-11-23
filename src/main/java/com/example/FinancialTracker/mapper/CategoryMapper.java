package com.example.FinancialTracker.mapper;

import com.example.FinancialTracker.entity.CategoryEntity;
import com.example.FinancialTracker.form.TransactionForm;

public class CategoryMapper {

    public static CategoryEntity toEntity(TransactionForm transactionForm) {
        return CategoryEntity.builder()
                .categoryName(transactionForm.getCategoryName())
                .build();
    }

}
