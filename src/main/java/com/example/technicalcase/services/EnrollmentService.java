package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.NotActiveCourseException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import com.example.technicalcase.services.exceptions.UniquenessViolationEnrollmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.technicalcase.enumerators.Status.ACTIVE;
import static java.lang.Boolean.FALSE;
import static java.util.Objects.isNull;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository repository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Enrollment save(Enrollment enrollment) {
        var student = userRepository.findByUsername(enrollment.getStudent().getUsername());
        var course = courseRepository.findByCode(enrollment.getCourse().getCode());

        validationNotFoundEntity(student, course);
        validationActiveCourse(course);
        validationUniquenessViolation(student, course);

        setData(enrollment, student, course);
        return repository.save(enrollment);
    }

    private void validationNotFoundEntity(User student, Course course) {
        if(isNull(course) || isNull(student)) {
            throw new ResourceNotFoundException();
        }
    }

    private void validationActiveCourse(Course course) {
        if (FALSE.equals(course.getStatus().equals(ACTIVE))){
            throw new NotActiveCourseException();
        }
    }

    private void validationUniquenessViolation(User student, Course course) {
        if (repository.findByStudentAndCourse(student, course).isPresent()) {
            throw new UniquenessViolationEnrollmentException();
        }
    }

    private static void setData(Enrollment enrollment, User student, Course course) {
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
    }
}