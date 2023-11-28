package com.example.FinancialTracker.service;

import com.example.FinancialTracker.service.impl.DefaultUserDetails;

public interface TokenService {

    String generateAccessToken(DefaultUserDetails userDetails);

    String generateRefreshToken(DefaultUserDetails userDetails);

    String parseToken(String token);

}
