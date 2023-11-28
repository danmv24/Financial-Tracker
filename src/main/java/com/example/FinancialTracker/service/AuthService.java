package com.example.FinancialTracker.service;

import com.example.FinancialTracker.form.UserForm;
import com.example.FinancialTracker.view.JwtView;

public interface AuthService {

    void create(UserForm userForm);

    JwtView authenticateUser(UserForm userForm);

}
