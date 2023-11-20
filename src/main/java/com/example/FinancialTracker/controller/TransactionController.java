package com.example.FinancialTracker.controller;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
