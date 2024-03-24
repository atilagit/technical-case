package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(UUID id, String name, String username, String email, Role role, LocalDateTime creationDate) {
}