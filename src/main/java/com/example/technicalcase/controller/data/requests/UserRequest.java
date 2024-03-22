package com.example.technicalcase.controller.data.requests;

import com.example.technicalcase.enumerators.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "Campo obrigatório") String name,
        @Pattern(regexp = "^[a-z]+$", message = "O username deve conter apenas letras minúsculas") String username,
        @Email(message = "Digite um email válido") String email,
        @NotBlank(message = "Campo obrigatório") String password,
        @NotNull(message = "Campo obrigatório") Role role,
        LocalDate creationDate
) {}