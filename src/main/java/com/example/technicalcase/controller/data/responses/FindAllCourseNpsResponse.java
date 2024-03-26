package com.example.technicalcase.controller.data.responses;

public record FindAllCourseNpsResponse(Double nps, String courseName, String courseCode, Long enrollmentQuantity) {
}