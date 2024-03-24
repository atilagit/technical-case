package com.example.technicalcase.controller.data.responses;

import com.example.technicalcase.enumerators.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record InsertCourseResponse(UUID id, String instructorUsername, String code, String description, String name, Status status, LocalDateTime creationDate, LocalDateTime inactivationDate) {
}