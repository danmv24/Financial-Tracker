package com.example.FinancialTracker.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TransactionType {
    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private String value;
}
