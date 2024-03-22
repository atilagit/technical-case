package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Role;

import java.time.LocalDate;

public record UserResponse(Long id, String name, String username, String email, Role role, LocalDate creationDate) {
}