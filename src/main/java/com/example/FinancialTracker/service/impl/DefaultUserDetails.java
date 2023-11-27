package com.example.FinancialTracker.service.impl;

import com.example.FinancialTracker.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class DefaultUserDetails implements UserDetails {
//
//    private Long id;
//
//    private String username;
//
//    private String password;
//
//    private String name;
//
//    private String surname;
//
//    public static UserDetails build() {
//
//    }

    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public String getSurname() {
        return user.getSurname();
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
