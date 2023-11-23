package com.example.FinancialTracker.controller;

import com.example.FinancialTracker.enums.TransactionType;
import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/add")
    public void add(@RequestPart("transaction") TransactionForm transactionForm) {
        transactionService.addTransaction(transactionForm);
    }

    @GetMapping("/all")
    public List<TransactionView> getAllTransactions() {
        return transactionService.allTransactions();
    }

    @GetMapping("/all/type")
    public Map<String, List<TransactionView>> getAllTransactionsByType() {
        return transactionService.allTransactionsByType();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable(value = "id") Long id) {
        transactionService.delete(id);
    }
}
