package com.example.technicalcase.repositories;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.enumerators.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Course findByCode(String code);
    Page<Course> findAllByStatus(Status status, Pageable pageable);
}