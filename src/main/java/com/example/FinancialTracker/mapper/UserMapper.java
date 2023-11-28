package com.example.FinancialTracker.mapper;

import com.example.FinancialTracker.entity.UserEntity;
import com.example.FinancialTracker.form.UserForm;

public class UserMapper {

    public static UserEntity toUserEntity(UserForm userForm, String password) {
        return UserEntity.builder()
                .username(userForm.getUsername())
                .name(userForm.getName())
                .surname(userForm.getSurname())
                .password(password)
                .build();
    }

}
