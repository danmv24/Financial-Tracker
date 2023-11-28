package com.example.FinancialTracker.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserForm {

    private String username;

    private String name;

    private String surname;

    private String password;

}
