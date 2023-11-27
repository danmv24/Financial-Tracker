package com.example.FinancialTracker.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "username",unique = true)
    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String password;

}
