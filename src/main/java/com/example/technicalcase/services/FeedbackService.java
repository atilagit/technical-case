package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Feedback;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.FeedbackRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import com.example.technicalcase.services.exceptions.StudentNotEnrolledException;
import com.example.technicalcase.services.exceptions.UniquenessViolationFeedbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Objects.isNull;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository repository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Transactional
    public Feedback save(Feedback feedback) {
        var student = userRepository.findByUsername(feedback.getStudent().getUsername());
        var course = courseRepository.findByCode(feedback.getCourse().getCode());

        validationNotFoundEntity(student, course);
        validationUniquenessViolation(student, course);
        validationUniquenessViolation(student, course);
        validationEnrollmentViolation(student, course);

        setData(feedback, student, course);
        return repository.save(feedback);
    }

    private void validationNotFoundEntity(User student, Course course) {
        if(isNull(course) || isNull(student)) {
            throw new ResourceNotFoundException();
        }
    }

    private void validationUniquenessViolation(User student, Course course) {
        if (repository.findByStudentAndCourse(student, course).isPresent()) {
            throw new UniquenessViolationFeedbackException();
        }
    }

    private void validationEnrollmentViolation(User student, Course course) {
        if (enrollmentRepository.findByStudentAndCourse(student, course).isEmpty()) {
            throw new StudentNotEnrolledException();
        }
    }

    private static void setData(Feedback feedback, User student, Course course) {
        feedback.setStudent(student);
        feedback.setCourse(course);
        feedback.setFeedbackDate(LocalDateTime.now(ZoneId.of("UTC")));
    }
}