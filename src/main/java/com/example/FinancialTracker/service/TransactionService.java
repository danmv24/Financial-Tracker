package com.example.FinancialTracker.service;

import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    void addTransaction(TransactionForm transactionForm, HttpServletRequest request);

    List<TransactionView> allTransactions(HttpServletRequest request);

    Map<TransactionType, List<TransactionView>> allTransactionsByType(HttpServletRequest request);

    void delete(Long id, HttpServletRequest request);

}
