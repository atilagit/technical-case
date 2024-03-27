package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.enumerators.Role;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    SaveCourseService service;

    @Test
    public void shouldSaveCourse_WhenInstructorExists() {
        // Arrange
        User instructor = new User("bob");
        instructor.setRole(Role.INSTRUCTOR);
        Course course = new Course();
        course.setInstructor(instructor);
        when(userRepository.getReferenceByUsername(anyString())).thenReturn(instructor);
        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Course result = service.execute(course);

        // Assert
        verify(userRepository, times(1)).getReferenceByUsername(any(String.class));
        verify(courseRepository, times(1)).save(any(Course.class));
        assertNotNull(result);
    }
}
