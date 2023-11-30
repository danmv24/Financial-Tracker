package com.example.FinancialTracker.mapper;

import com.example.FinancialTracker.entity.UserEntity;
import com.example.FinancialTracker.form.UserForm;
import com.example.FinancialTracker.service.impl.DefaultUserDetails;

public class UserMapper {

    public static UserEntity toUserEntity(UserForm userForm, String password) {
        return UserEntity.builder()
                .username(userForm.getUsername())
                .name(userForm.getName())
                .surname(userForm.getSurname())
                .password(password)
                .build();
    }

    public static DefaultUserDetails toUserDetails(UserEntity user) {
        return DefaultUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .build();
    }

}
