package com.example.technicalcase.controller.data.requests;

import jakarta.validation.constraints.NotBlank;

public record InsertEnrollmentRequest(@NotBlank String studentUsername, @NotBlank String courseCode) {
}