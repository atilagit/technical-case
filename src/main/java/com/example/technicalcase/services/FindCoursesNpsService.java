package com.example.technicalcase.services;

import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FindCoursesNpsService {

    @Autowired
    CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public Page<CourseProjection> execute(Integer minEnrollments, Pageable pageable) {
        return courseRepository.getCourseProjectionList(minEnrollments, pageable);
    }
}