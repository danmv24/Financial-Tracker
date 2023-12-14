package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.exception.TransactionException;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.mapper.TransactionMapper;
import com.example.FinancialTracker.repository.CategoryRepository;
import com.example.FinancialTracker.repository.TransactionRepository;
import com.example.FinancialTracker.service.TokenService;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TokenService tokenService;

    private final CategoryRepository categoryRepository;

    @Override
    public void addTransaction(TransactionForm transactionForm, HttpServletRequest request) {
        Long userId = tokenService.parseToken(request);

        if (transactionForm.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionException(HttpStatus.BAD_REQUEST, "Amount cannot be zero");
        }

        Long categoryId = categoryRepository.findByCategoryNameIgnoreCase(transactionForm.getCategoryName()).orElseThrow(
                () -> new TransactionException(HttpStatus.NOT_FOUND, "The category with the name " + transactionForm.getCategoryName() + " was not found!")).getId();

        transactionRepository.save(TransactionMapper.toEntity(transactionForm, categoryId, userId));
    }

    @Override
    public List<TransactionView> allTransactions(HttpServletRequest request) {
        Long userId = tokenService.parseToken(request);

        List<TransactionEntity> transactions = transactionRepository.findAllByUserIdOrderByDateDesc(userId);

        return transactions.stream()
                .map(transaction -> {
                    TransactionView transactionView = TransactionMapper.toView(transaction);
                    categoryRepository.findById(transaction.getCategoryId())
                            .ifPresent(category -> transactionView.setCategory(category.getCategoryName()));
                    return transactionView;
                }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id, HttpServletRequest request) {
        Long userId = tokenService.parseToken(request);

        TransactionEntity transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionException(HttpStatus.NOT_FOUND, "Transaction with id " + id + " does not exist"));

        if (!userId.equals(transaction.getUserId())) {
            throw new TransactionException(HttpStatus.FORBIDDEN, "User does not have permission to delete this transaction");
        }

        transactionRepository.delete(transaction);

    }
}
