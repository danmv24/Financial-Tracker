package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.repository.UserRepository;
import com.example.FinancialTracker.service.TokenService;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class DefaultTokenService implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    @Override
    public String generateAccessToken(DefaultUserDetails userDetails) {
        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    @Override
    public String generateRefreshToken(DefaultUserDetails userDetails) {
        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(20, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    @Override
    public Long parseToken(HttpServletRequest request) {
        try {
            String headerAuth = request.getHeader("Authorization");
            String token = headerAuth.substring(7);
            SignedJWT decodedJWT = SignedJWT.parse(token);
            String username = decodedJWT.getJWTClaimsSet().getSubject();
            return userRepository.findByUsername(username).get().getId();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
