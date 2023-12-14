package com.example.FinancialTracker.service;

import com.example.FinancialTracker.service.impl.DefaultUserDetails;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    String generateAccessToken(DefaultUserDetails userDetails);

    String generateRefreshToken(DefaultUserDetails userDetails);

    Long parseToken(HttpServletRequest request);

}
