package com.example.technicalcase.controller.data.requests;

import com.example.technicalcase.enumerators.Role;
import jakarta.validation.constraints.*;

public record UserRequest(
        @NotBlank(message = "Campo obrigatório")
        String name,

        @Pattern(regexp = "^[a-z]+$", message = "O username deve conter apenas letras minúsculas")
        @Size(max = 20, message = "Ultrapassou o limite máximo de caractes")
        @NotBlank(message = "Campo obrigatório")
        String username,

        @Email(message = "Digite um email válido")
        @NotBlank(message = "Campo obrigatório")
        String email,

        @NotBlank(message = "Campo obrigatório")
        String password,

        @NotNull(message = "Campo obrigatório")
        Role role
) {}