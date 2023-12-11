package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.CategoryEntity;
import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.entity.UserEntity;
import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.exception.TransactionException;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.mapper.CategoryMapper;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TokenService tokenService;

    private final CategoryRepository categoryRepository;

    @Override
    public void addTransaction(TransactionForm transactionForm, HttpServletRequest request) {
        UserEntity user = tokenService.parseToken(request);

        if (transactionForm.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionException(HttpStatus.BAD_REQUEST, "Amount cannot be zero");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findByCategoryName(transactionForm.getCategoryName());

        if (categoryOptional.isPresent()) {
            transactionRepository.save(TransactionMapper.toEntity(transactionForm, categoryOptional.get(), user));
        } else {
            CategoryEntity category = categoryRepository.save(CategoryMapper.toEntity(transactionForm));
            transactionRepository.save(TransactionMapper.toEntity(transactionForm, category, user));
        }
    }

    @Override
    public List<TransactionView> allTransactions(HttpServletRequest request) {
        UserEntity user = tokenService.parseToken(request);

        List<TransactionEntity> transactions = transactionRepository.findAllByUserId(user.getId());

        return transactions.stream()
                .map(TransactionMapper::toView)
                .collect(Collectors.toList());
    }

    @Override
    public Map<TransactionType, List<TransactionView>> allTransactionsByType(HttpServletRequest request) {
        UserEntity user = tokenService.parseToken(request);

        List<TransactionEntity> transactions = transactionRepository.findAllByUserId(user.getId());

        List<TransactionView> transactionViews = transactions.stream()
                .map(TransactionMapper::toView)
                .collect(Collectors.toList());

        return transactionViews.stream()
                .collect(Collectors.groupingBy(TransactionView::getTransactionType));

    }

    @Override
    public void delete(Long id, HttpServletRequest request) {
        UserEntity user = tokenService.parseToken(request);

        TransactionEntity transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionException(HttpStatus.NOT_FOUND, "Transaction with id " + id + " does not exist"));

        if (!user.getId().equals(transaction.getUser().getId())) {
            throw new TransactionException(HttpStatus.FORBIDDEN, "User does not have permission to delete this transaction");
        }

        transactionRepository.delete(transaction);

    }
}
