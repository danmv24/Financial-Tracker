package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import java.util.List;

public interface TransactionService {

    void addTransaction(TransactionForm transactionForm);

    List<TransactionView> allTransactions();

}
