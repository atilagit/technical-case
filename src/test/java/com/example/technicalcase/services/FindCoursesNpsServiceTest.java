package com.example.technicalcase.services;

import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindCoursesNpsServiceTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private FindCoursesNpsService service;

    @Test
    public void shouldCallRepositoryAndResponse() {
        // ARRANGE
        when(repository.getCourseProjectionList(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        // ACT
        Page<CourseProjection> result = service.execute(5, Pageable.unpaged());

        // ASSERT
        verify(repository, times(1)).getCourseProjectionList(any(Integer.class), any(Pageable.class));
        assertNotNull(result);
    }
}