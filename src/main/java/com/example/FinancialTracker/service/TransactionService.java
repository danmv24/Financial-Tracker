package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.view.TransactionView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TransactionService {

    void addTransaction(TransactionForm transactionForm, HttpServletRequest request);

    List<TransactionView> allTransactions(HttpServletRequest request);

    void delete(Long id, HttpServletRequest request);

}
