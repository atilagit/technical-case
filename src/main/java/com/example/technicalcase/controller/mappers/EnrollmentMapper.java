package com.example.technicalcase.controller.mappers;

import com.example.technicalcase.controller.data.requests.InsertEnrollmentRequest;
import com.example.technicalcase.controller.data.responses.InsertEnrollmentResponse;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;

public class EnrollmentMapper {
    public static Enrollment mapToEntity(InsertEnrollmentRequest request) {
        var enrollment = new Enrollment();
        var student = new User(request.studentUsername());
        var course = new Course(request.courseCode());

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollment;
    }

    public static InsertEnrollmentResponse mapToInsertEnrollmentResponse(Enrollment enrollment) {
        return new InsertEnrollmentResponse(enrollment.getId(), enrollment.getStudent().getUsername(),
                enrollment.getCourse().getCode(), enrollment.getEnrollmentDate());
    }
}