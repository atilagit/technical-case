package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.AlreadyInactiveStatusException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.technicalcase.enumerators.Status.ACTIVE;
import static com.example.technicalcase.enumerators.Status.INACTIVE;
import static java.util.Objects.isNull;

@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Course save(Course course) {
        User user = userRepository.getReferenceByUsername(course.getInstructor().getUsername());
        course.setCreationDate(LocalDate.now());
        course.setInstructor(user);
        course.setStatus(ACTIVE);
        return repository.save(course);
    }

    @Transactional
    public Course inactive(String code) {
        Course course = repository.findByCode(code);
        if(isNull(course)) {
            throw new ResourceNotFoundException();
        }
        if(course.getStatus().equals(INACTIVE)) {
            throw new AlreadyInactiveStatusException();
        }
        course.setStatus(INACTIVE);
        course.setInactivationDate(LocalDate.now());
        return repository.save(course);
    }

    @Transactional(readOnly = true)
    public Page<Course> findAll(Status status, Pageable pageable) {
        if(isNull(status)) {
            return repository.findAll(pageable);
        }
        return repository.findAllByStatus(status, pageable);
    }
}