package com.example.technicalcase.controller.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InsertCourseRequest(

        @NotBlank
        String instructorUsername,

        @NotBlank
        @Size(min = 2, max = 10)
        @Pattern(regexp = "^[a-zA-Z]+(?:-[a-zA-Z]+)*$", message = MESSAGE_REGEX_VALIDATION_CODE)
        String code,

        @NotBlank
        String description,

        @NotBlank
        String name) {

    public static final String MESSAGE_REGEX_VALIDATION_CODE = "The course code must be textual, without spaces, numeric characters or special characters, but can be separated by - , for example: java-test .";
}