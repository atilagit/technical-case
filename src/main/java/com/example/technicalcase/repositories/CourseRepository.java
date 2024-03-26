package com.example.technicalcase.repositories;

import com.example.technicalcase.entities.Course;
import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.enumerators.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Course findByCode(String code);
    Page<Course> findAllByStatus(Status status, Pageable pageable);

    @Query("SELECT c.code AS courseCode, c.name AS courseName, " +
            "COUNT(DISTINCT e.id) AS enrollmentQuantity, " +
            "CASE WHEN COUNT(cf.id) > 0 THEN " +
            "((SUM(CASE WHEN cf.grade >= 9 THEN 1 ELSE 0 END) - SUM(CASE WHEN cf.grade <= 6 THEN 1 ELSE 0 END)) * 100.0 / COUNT(cf.id)) " +
            "ELSE 0.0 END AS nps " +
            "FROM Course c " +
            "LEFT JOIN c.enrollments e " +
            "LEFT JOIN c.courseFeedbacks cf " +
            "GROUP BY c.code, c.name " +
            "HAVING COUNT(DISTINCT e.id) > :minEnrollments")
    Page<CourseProjection> getCourseProjectionList(@Param("minEnrollments") Integer minEnrollments, Pageable pageable);
}