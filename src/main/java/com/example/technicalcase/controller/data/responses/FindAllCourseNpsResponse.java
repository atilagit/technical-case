package com.example.technicalcase.controller.data.responses;

import java.time.LocalDateTime;

public record FindAllCourseNpsResponse(Double nps, String courseName, String courseCode, String instructorUsername, Long enrollmentQuantity, LocalDateTime npsDate) {
}