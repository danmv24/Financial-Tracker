package com.example.FinancialTracker.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class JwtView {

    private String accessToken;

    private String refreshToken;

}
