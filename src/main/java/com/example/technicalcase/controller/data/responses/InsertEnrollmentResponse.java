package com.example.technicalcase.controller.data.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record InsertEnrollmentResponse(UUID id, String studentUsername, String courseCode, LocalDateTime enrollmentDate) {
}