package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Role;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(UUID id, String name, String username, String email, Role role, LocalDate creationDate) {
}