package com.example.FinancialTracker.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Username can't be null!")
    private String username;

    @NotBlank(message = "Name can't be null!")
    private String name;

    @NotBlank(message = "Surname can't be null!")
    private String surname;

    @NotBlank(message = "Password can't be null!")
    private String password;

}
