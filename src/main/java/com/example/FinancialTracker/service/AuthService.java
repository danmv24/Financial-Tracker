package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.UserForm;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    void create(UserForm userForm);

    ResponseEntity<String> authenticateUser(UserForm userForm);

}
