package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.exception.UserAlreadyExistsException;
import com.example.FinancialTracker.form.UserForm;
import com.example.FinancialTracker.mapper.UserMapper;
import com.example.FinancialTracker.repository.UserRepository;
import com.example.FinancialTracker.service.AuthService;
import com.example.FinancialTracker.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    @Override
    public void create(UserForm userForm) {
        if (userRepository.existsByUsername(userForm.getUsername())) {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "User with username "+userForm.getUsername()+
                    " already exists");
        }

        userRepository.save(UserMapper.toUserEntity(userForm, passwordEncoder.encode(userForm.getPassword())));
    }

    @Override
    public ResponseEntity<String> authenticateUser(UserForm userForm) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword()));

        DefaultUserDetails userDetails = (DefaultUserDetails) auth.getPrincipal();

        String accessToken = tokenService.generateAccessToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + accessToken);
        httpHeaders.add("Refresh-Token", "Refresh " + refreshToken);

        return new ResponseEntity<>("Login successful", httpHeaders, HttpStatus.OK);
    }



}
