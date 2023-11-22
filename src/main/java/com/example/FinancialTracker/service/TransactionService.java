package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    void addTransaction(TransactionForm transactionForm);

    List<TransactionView> allTransactions();

    Map<String, List<TransactionView>> allTransactionsByType();

}
