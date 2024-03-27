package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FindAllCourseServiceTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private FindAllCourseService service;

    private Page<Course> mockCoursePage;

    @Test
    public void shouldCallRepositoryWithStatusParameter_WhenStatusIsNonNull() {
        // ARRANGE
        var status = Status.ACTIVE;
        var pageable = Pageable.unpaged();
        when(repository.findAllByStatus(any(Status.class), any(Pageable.class))).thenReturn(mockCoursePage);

        // ACT
        Page<Course> result = service.execute(status, pageable);

        // ASSERT
        verify(repository, times(1)).findAllByStatus(any(Status.class), any(Pageable.class));
        verify(repository, never()).findAll(any(Pageable.class));
        assertEquals(mockCoursePage, result);
    }

    @Test
    public void shouldCallRepositoryWithoutStatusParameter_WhenStatusIsNull() {
        // ARRANGE
        var pageable = Pageable.unpaged();
        when(repository.findAll(any(Pageable.class))).thenReturn(mockCoursePage);

        // ACT
        Page<Course> result = service.execute(null, pageable);

        // ASSERT
        verify(repository, times(1)).findAll(any(Pageable.class));
        verify(repository, never()).findAllByStatus(any(Status.class), any(Pageable.class));
        assertEquals(mockCoursePage, result);
    }

    @BeforeEach
    public void setup() {
        List<Course> mockCourses;

        Course course1 = new Course();
        course1.setId(UUID.randomUUID());
        course1.setName("Course 1");

        Course course2 = new Course();
        course2.setId(UUID.randomUUID());
        course2.setName("Course 2");

        mockCourses = List.of(course1, course2);

        // Mock the page
        mockCoursePage = new PageImpl<>(mockCourses);
    }
}
