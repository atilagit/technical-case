package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
public class FindAllCourseService {

    @Autowired
    CourseRepository repository;

    @Transactional(readOnly = true)
    public Page<Course> execute(Status status, Pageable pageable) {
        if(isNull(status)) {
            return repository.findAll(pageable);
        }
        return repository.findAllByStatus(status, pageable);
    }
}