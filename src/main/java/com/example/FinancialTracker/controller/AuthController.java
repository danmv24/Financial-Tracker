package com.example.FinancialTracker.controller;

import com.example.FinancialTracker.form.UserForm;
import com.example.FinancialTracker.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestPart("user") UserForm userForm) {
        authService.create(userForm);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestPart("user") UserForm userForm) {
        return authService.authenticateUser(userForm);
    }
}
