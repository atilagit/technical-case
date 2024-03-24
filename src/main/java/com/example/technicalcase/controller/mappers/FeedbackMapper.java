package com.example.technicalcase.controller.mappers;

import com.example.technicalcase.controller.data.requests.InsertCourseFeedbackRequest;
import com.example.technicalcase.controller.data.responses.InsertCourseFeedbackResponse;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.entities.User;

import static org.springframework.beans.BeanUtils.copyProperties;

public class FeedbackMapper {
    public static CourseFeedback mapToEntity(InsertCourseFeedbackRequest request) {
        var feedback = new CourseFeedback();
        copyProperties(request, feedback);

        var student = new User(request.studentUsername());
        var course = new Course(request.courseCode());

        feedback.setStudent(student);
        feedback.setCourse(course);
        return feedback;
    }

    public static InsertCourseFeedbackResponse mapToInsertFeedbackResponse(CourseFeedback feedback) {
        return new InsertCourseFeedbackResponse(feedback.getId(), feedback.getStudent().getUsername(),
                feedback.getCourse().getCode(), feedback.getFeedbackDate(), feedback.getGrade(), feedback.getReason());
    }
}