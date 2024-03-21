package com.example.technicalcase.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;
    private LocalDate creationDate;

    public enum Role {
        ESTUDANTE, INSTRUTOR, ADMIN
    }
}
