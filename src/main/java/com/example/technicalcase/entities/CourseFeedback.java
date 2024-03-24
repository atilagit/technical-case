package com.example.technicalcase.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_COURSE_FEEDBACK", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class CourseFeedback implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Integer grade;
    private String reason;
    private LocalDateTime feedbackDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseFeedback courseFeedback)) return false;
        return Objects.equals(getId(), courseFeedback.getId()) && Objects.equals(getStudent(), courseFeedback.getStudent()) && Objects.equals(getCourse(), courseFeedback.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudent(), getCourse());
    }
}