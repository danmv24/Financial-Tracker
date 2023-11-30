package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.CategoryEntity;
import com.example.FinancialTracker.entity.TransactionEntity;
import com.example.FinancialTracker.entity.UserEntity;
import com.example.FinancialTracker.exception.TransactionException;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.mapper.CategoryMapper;
import com.example.FinancialTracker.mapper.TransactionMapper;
import com.example.FinancialTracker.repository.CategoryRepository;
import com.example.FinancialTracker.repository.TransactionRepository;
import com.example.FinancialTracker.repository.UserRepository;
import com.example.FinancialTracker.service.TokenService;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TokenService tokenService;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public void addTransaction(TransactionForm transactionForm, HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String token = headerAuth.substring(7, headerAuth.length());

        String username = tokenService.parseToken(token);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " +username+" not found!"));

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
