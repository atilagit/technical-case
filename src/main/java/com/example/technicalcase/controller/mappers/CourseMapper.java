package com.example.technicalcase.controller.mappers;

import com.example.technicalcase.controller.data.requests.InsertCourseRequest;
import com.example.technicalcase.controller.data.responses.InsertCourseResponse;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.User;

import static org.springframework.beans.BeanUtils.copyProperties;

public class CourseMapper {
    public static Course mapToCourse(InsertCourseRequest request) {
        var course = new Course();
        copyProperties(request, course);

        var instructor = new User();
        instructor.setUsername(request.instructorUsername());
        course.setInstructor(instructor);

        return course;
    }

    public static InsertCourseResponse mapToInsertCourseResponse(Course course) {
        return new InsertCourseResponse(course.getId(), course.getInstructor().getUsername(), course.getCode(),
                course.getDescription(), course.getName(), course.getStatus(), course.getCreationDate(),
                course.getInactivationDate());
    }
}
