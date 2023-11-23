package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.CategoryEntity;
import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.exception.TransactionException;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.mapper.CategoryMapper;
import com.example.FinancialTracker.mapper.TransactionMapper;
import com.example.FinancialTracker.repository.CategoryRepository;
import com.example.FinancialTracker.repository.TransactionRepository;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;

    private CategoryRepository categoryRepository;

    @Override
    public void addTransaction(TransactionForm transactionForm) {
        if (transactionForm.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionException(HttpStatus.BAD_REQUEST, "Amount cannot be zero");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findByCategoryName(transactionForm.getCategoryName());

        if (categoryOptional.isPresent()) {
            transactionRepository.save(TransactionMapper.toEntity(transactionForm, categoryOptional.get()));
        } else {
            CategoryEntity category = categoryRepository.save(CategoryMapper.toEntity(transactionForm));
            transactionRepository.save(TransactionMapper.toEntity(transactionForm, category));
        }
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

    @Override
    public Map<String, List<TransactionView>> allTransactionsByType() {
        List<TransactionEntity> transactions = transactionRepository.findAll();

        List<TransactionView> transactionViews = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            transactionViews.add(TransactionMapper.toView(transaction));
        }

        Map<String, List<TransactionView>> transactionsByType = transactionViews.stream()
                .collect(Collectors.groupingBy(TransactionView::getTransactionType));

        return transactionsByType;

    }

    @Override
    public void delete(Long id) {
        Optional<TransactionEntity> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
        } else {
            throw new TransactionException(HttpStatus.NOT_FOUND, "Transaction with id " + id + " does not exist");
        }
    }
}
