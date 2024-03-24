package com.example.technicalcase.repositories;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.entities.projections.CourseFeedbackProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, UUID> {
    Optional<CourseFeedback> findByStudentAndCourse(User student, Course course);

    @Query("SELECT c.name AS courseName, c.code AS courseCode, " +
            "((SUM(CASE WHEN cf.grade >= 9 THEN 1 ELSE 0 END) - " +
            "SUM(CASE WHEN cf.grade <= 6 THEN 1 ELSE 0 END)) * " +
            "100.0 / COUNT(cf)) AS nps " +
            "FROM CourseFeedback cf " +
            "JOIN cf.course c " +
            "GROUP BY c.name, c.code")
    List<CourseFeedbackProjection> getCourseNps();
}