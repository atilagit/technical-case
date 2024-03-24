package com.example.technicalcase.controller.mappers;

import com.example.technicalcase.controller.data.requests.InsertFeedbackRequest;
import com.example.technicalcase.controller.data.responses.InsertFeedbackResponse;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Feedback;
import com.example.technicalcase.entities.User;

import static org.springframework.beans.BeanUtils.copyProperties;

public class FeedbackMapper {
    public static Feedback mapToEntity(InsertFeedbackRequest request) {
        var feedback = new Feedback();
        copyProperties(request, feedback);

        var student = new User(request.studentUsername());
        var course = new Course(request.courseCode());

        feedback.setStudent(student);
        feedback.setCourse(course);
        return feedback;
    }

    public static InsertFeedbackResponse mapToInsertFeedbackResponse(Feedback feedback) {
        return new InsertFeedbackResponse(feedback.getId(), feedback.getStudent().getUsername(),
                feedback.getCourse().getCode(), feedback.getFeedbackDate(), feedback.getGrade(), feedback.getReason());
    }
}