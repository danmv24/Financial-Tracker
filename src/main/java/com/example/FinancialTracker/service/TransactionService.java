package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    void addTransaction(TransactionForm transactionForm, HttpServletRequest request);

    List<TransactionView> allTransactions();

    Map<String, List<TransactionView>> allTransactionsByType();

    void delete(Long id);

}
