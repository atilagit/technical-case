package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.EnrollmentRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.NotActiveCourseException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import com.example.technicalcase.services.exceptions.UniquenessViolationEnrollmentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveEnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    SaveEnrollmentService service;

    @Test
    public void shouldSaveEnrollment_WhenValidStudentAndCourse() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        course.setStatus(Status.ACTIVE);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);
        when(enrollmentRepository.findByStudentAndCourse(any(User.class), any(Course.class))).thenReturn(java.util.Optional.empty());
        when(enrollmentRepository.save(any(Enrollment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Enrollment result = service.execute(enrollment);

        // Assert
        assertNotNull(result);
        assertEquals(student, result.getStudent());
        assertEquals(course, result.getCourse());
        assertNotNull(result.getEnrollmentDate());
    }

    @Test
    public void shouldThrowResourceNotFoundException_WhenStudentOrCourseNotFound() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        course.setStatus(Status.ACTIVE);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(courseRepository.findByCode(anyString())).thenReturn(null);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> service.execute(enrollment));
    }

    @Test
    public void shouldThrowNotActiveCourseException_WhenCourseIsNotActive() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        course.setStatus(Status.INACTIVE);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);

        // Act and Assert
        assertThrows(NotActiveCourseException.class, () -> service.execute(enrollment));
    }

    @Test
    public void shouldThrowUniquenessViolationEnrollmentException_WhenEnrollmentAlreadyExists() {
        // Arrange
        User student = new User("goku");
        Course course = new Course("java");
        course.setStatus(Status.ACTIVE);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        when(userRepository.findByUsername(anyString())).thenReturn(student);
        when(courseRepository.findByCode(anyString())).thenReturn(course);
        when(enrollmentRepository.findByStudentAndCourse(any(User.class), any(Course.class))).thenReturn(java.util.Optional.of(new Enrollment()));

        // Act and Assert
        assertThrows(UniquenessViolationEnrollmentException.class, () -> service.execute(enrollment));
    }
}