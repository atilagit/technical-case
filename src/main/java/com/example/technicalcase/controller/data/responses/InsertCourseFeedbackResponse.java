package com.example.technicalcase.controller.data.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record InsertCourseFeedbackResponse(UUID id, String studentUsername, String courseCode, LocalDateTime feedbackDate, Integer grade, String reason) {
}