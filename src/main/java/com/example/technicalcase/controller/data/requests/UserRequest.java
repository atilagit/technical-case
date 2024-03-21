package com.example.technicalcase.controller.data.requests;

import java.time.LocalDate;

public record UserRequest(String name, String username, String email, String password, String role, LocalDate creationDate) {
}