package com.example.FinancialTracker.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RuntimeException{

    private HttpStatus errorCode;

    private String errorMessage;

    public TransactionException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
