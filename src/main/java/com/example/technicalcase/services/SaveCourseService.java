package com.example.technicalcase.services;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.technicalcase.enumerators.Status.ACTIVE;

@Service
public class SaveCourseService {

    @Autowired
    CourseRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Course execute(Course course) {
        User user = userRepository.getReferenceByUsername(course.getInstructor().getUsername());
        course.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        course.setInstructor(user);
        course.setStatus(ACTIVE);
        return repository.save(course);
    }
}