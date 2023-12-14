package com.example.FinancialTracker.controller;

import com.example.FinancialTracker.form.TransactionForm;
import com.example.FinancialTracker.service.TransactionService;
import com.example.FinancialTracker.view.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        transactionService.delete(id, request);
    }
}
