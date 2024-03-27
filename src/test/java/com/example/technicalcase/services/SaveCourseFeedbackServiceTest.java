package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.CourseFeedbackRepository;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import com.example.technicalcase.services.exceptions.StudentNotEnrolledException;
import com.example.technicalcase.services.exceptions.UniquenessViolationFeedbackException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveCourseFeedbackServiceTest {

    @Mock
    private CourseFeedbackRepository repository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    SaveCourseFeedbackService service;

    @Test
    public void shouldThrowResourceNotFoundException_WhenStudentOrCourseNotFound() {
        // Arrange
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(courseRepository.findByCode(anyString())).thenReturn(null);
        var courseFeedback = new CourseFeedback();
        courseFeedback.setStudent(new User("nonExisting"));
        courseFeedback.setCourse(new Course("nonExisting"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> service.execute(courseFeedback));
    }

    @Test
    public void shouldThrowUniquenessViolationFeedbackException_WhenFeedbackAlreadyExists() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        CourseFeedback existingFeedback = new CourseFeedback();
        existingFeedback.setStudent(student);
        existingFeedback.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);
        when(repository.findByStudentAndCourse(student, course)).thenReturn(Optional.of(existingFeedback));

        // Act and Assert
        assertThrows(UniquenessViolationFeedbackException.class, () -> service.execute(existingFeedback));
    }

    @Test
    public void shouldThrowStudentNotEnrolledException_WhenStudentIsNotEnrolledInCourse() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        CourseFeedback existingFeedback = new CourseFeedback();
        existingFeedback.setStudent(student);
        existingFeedback.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);
        when(enrollmentRepository.findByStudentAndCourse(student, course)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(StudentNotEnrolledException.class, () -> service.execute(existingFeedback));
    }

    @Test
    public void shouldSaveCourseFeedback_WhenAllValidationsPass() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        CourseFeedback feedback = new CourseFeedback();
        feedback.setStudent(student);
        feedback.setCourse(course);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);
        when(repository.findByStudentAndCourse(student, course)).thenReturn(Optional.empty());
        when(enrollmentRepository.findByStudentAndCourse(student, course)).thenReturn(Optional.of(enrollment));
        when(repository.save(any(CourseFeedback.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CourseFeedback result = service.execute(feedback);

        // Assert
        assertNotNull(result);
        assertEquals(student, result.getStudent());
        assertEquals(course, result.getCourse());
        assertNotNull(result.getFeedbackDate());
    }
}
