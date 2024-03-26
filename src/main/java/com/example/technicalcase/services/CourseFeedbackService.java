package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.observer.Observer;
import com.example.technicalcase.observer.Subject;
import com.example.technicalcase.repositories.CourseFeedbackRepository;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import com.example.technicalcase.services.exceptions.StudentNotEnrolledException;
import com.example.technicalcase.services.exceptions.UniquenessViolationFeedbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CourseFeedbackService implements Subject {

    @Autowired
    CourseFeedbackRepository repository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private final List<Observer> observers = new ArrayList<>();

    @Transactional
    public CourseFeedback save(CourseFeedback courseFeedback) {
        var student = userRepository.findByUsername(courseFeedback.getStudent().getUsername());
        var course = courseRepository.findByCode(courseFeedback.getCourse().getCode());

        validationNotFoundEntity(student, course);
        validationUniquenessViolation(student, course);
        validationEnrollmentViolation(student, course);

        setData(courseFeedback, student, course);
        courseFeedback = repository.save(courseFeedback);

        notifyObservers(courseFeedback);
        return courseFeedback;
    }

    @Transactional(readOnly = true)
    public Page<CourseProjection> findCoursesNps(Integer minEnrollments, Pageable pageable) {
        return courseRepository.getCourseProjectionList(minEnrollments, pageable);
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

    private static void setData(CourseFeedback courseFeedback, User student, Course course) {
        courseFeedback.setStudent(student);
        courseFeedback.setCourse(course);
        courseFeedback.setFeedbackDate(LocalDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void notifyObservers(Object data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}