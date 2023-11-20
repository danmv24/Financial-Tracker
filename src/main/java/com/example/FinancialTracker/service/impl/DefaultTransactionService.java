package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.mapper.TransactionMapper;
import com.example.FinancialTracker.repository.TransactionRepository;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;

    @Override
    public void addTransaction(TransactionForm transactionForm) {
        transactionRepository.save(TransactionMapper.toEntity(transactionForm));
    }

    @Override
    public List<TransactionView> allTransactions() {
        List<TransactionEntity> transactions = transactionRepository.findAll();

        List<TransactionView> transactionViews = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            transactionViews.add(TransactionMapper.toView(transaction));
        }

        return transactionViews;
    }
}
