package com.example.technicalcase.controller.data.requests;

import com.example.technicalcase.enumerators.Role;

import java.time.LocalDate;

public record UserRequest(String name, String username, String email, String password, Role role, LocalDate creationDate) {
}