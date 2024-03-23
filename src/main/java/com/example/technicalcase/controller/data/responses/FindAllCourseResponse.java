package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Status;

import java.time.LocalDate;

public record FindAllCourseResponse(String instructorUsername, String code, String description, String name, Status status, LocalDate creationDate, LocalDate inactivationDate) {
}