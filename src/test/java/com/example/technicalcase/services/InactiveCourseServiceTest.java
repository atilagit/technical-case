package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.services.exceptions.AlreadyInactiveStatusException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InactiveCourseServiceTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    InactiveCourseService service;

    @Test
    public void shouldThrowResourceNotFoundException_WhenCourseDoesNotExist() {
        // Arrange
        String code = "nonExistingCourse";
        when(repository.findByCode(code)).thenReturn(null);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> service.execute(code));
    }

    @Test
    public void shouldThrowAlreadyInactiveStatusException_WhenCourseIsAlreadyInactive() {
        // Arrange
        String code = "inactiveCourse";
        Course inactiveCourse = new Course();
        inactiveCourse.setStatus(Status.INACTIVE);
        when(repository.findByCode(code)).thenReturn(inactiveCourse);

        // Act and Assert
        assertThrows(AlreadyInactiveStatusException.class, () -> service.execute(code));
    }

    @Test
    public void shouldSetCourseAsInactive_WhenCourseIsActive() {
        // Arrange
        String code = "activeCourse";
        Course activeCourse = new Course();
        activeCourse.setStatus(Status.ACTIVE);
        when(repository.findByCode(code)).thenReturn(activeCourse);
        when(repository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Course result = service.execute(code);

        // Assert
        assertNotNull(result);
        assertEquals(Status.INACTIVE, result.getStatus());
        assertNotNull(result.getInactivationDate());
    }
}
