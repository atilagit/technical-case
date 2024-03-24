package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class StudentNotEnrolledException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String uniqueness_violation_message = "This user is not enrolled in this course";

	public StudentNotEnrolledException() {
		super(uniqueness_violation_message);
	}
}