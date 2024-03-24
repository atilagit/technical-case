package com.example.technicalcase.controller.data.requests;

import jakarta.validation.constraints.*;

public record InsertCourseFeedbackRequest(
        @NotBlank String studentUsername,
        @NotBlank String courseCode,
        @NotNull @Min(0) @Max(10) Integer grade,
        @NotBlank @Size(min = 10, max = 100) String reason) {
}