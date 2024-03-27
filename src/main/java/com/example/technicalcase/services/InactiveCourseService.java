package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.services.exceptions.AlreadyInactiveStatusException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.technicalcase.enumerators.Status.INACTIVE;
import static java.util.Objects.isNull;

@Service
public class InactiveCourseService {

    @Autowired
    CourseRepository repository;

    @Transactional
    public Course execute(String code) {
        Course course = repository.findByCode(code);
        if(isNull(course)) {
            throw new ResourceNotFoundException();
        }
        if(course.getStatus().equals(INACTIVE)) {
            throw new AlreadyInactiveStatusException();
        }
        course.setStatus(INACTIVE);
        course.setInactivationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(course);
    }
}