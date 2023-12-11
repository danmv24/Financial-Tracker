package com.example.FinancialTracker.controller;

import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/add")
    public void add(@RequestPart("transaction") TransactionForm transactionForm, HttpServletRequest request) {
        transactionService.addTransaction(transactionForm, request);
    }

    @GetMapping("/all")
    public List<TransactionView> getAllTransactions(HttpServletRequest request) {
        return transactionService.allTransactions(request);
    }

    @GetMapping("/all/type")
    public Map<TransactionType, List<TransactionView>> getAllTransactionsByType(HttpServletRequest request) {
        return transactionService.allTransactionsByType(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        transactionService.delete(id, request);
    }
}
