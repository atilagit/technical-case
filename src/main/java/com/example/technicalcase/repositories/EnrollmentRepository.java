package com.example.technicalcase.repositories;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.Enrollment;
import com.example.technicalcase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}