package com.example.technicalcase.controller.data.responses;

import java.time.LocalDate;

public record UserResponse(Long id, String name, String username, String email, String role, LocalDate creationDate) {
}