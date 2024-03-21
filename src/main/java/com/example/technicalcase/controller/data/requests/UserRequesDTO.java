package com.example.technicalcase.controller.data.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequesDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate creationDate;
}
