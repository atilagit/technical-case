package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Role;

public record FindUserResponse(String name, String email, Role role) {
}