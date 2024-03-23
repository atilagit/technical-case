package com.example.technicalcase.controller.data.requests;

import com.example.technicalcase.enumerators.Role;
import com.example.technicalcase.services.validation.InsertUserValidation;
import jakarta.validation.constraints.*;

@InsertUserValidation
public record UserRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotNull Role role,
        @NotBlank @Size(min = 2, max = 20) @Pattern(regexp = "^[a-z]+$", message = REGEX_MESSAGE) String username) {
    public static final String REGEX_MESSAGE = "Username must contain only lowercase characters, no numerals and no spaces";
}