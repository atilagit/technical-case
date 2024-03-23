package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
        course.setStatus(Status.ACTIVE);
        return repository.save(course);
    }
}