package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class UniquenessViolationEnrollmentException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String uniqueness_violation_message = "a user cannot enroll more than once in the same course";

	public UniquenessViolationEnrollmentException() {
		super(uniqueness_violation_message);
	}
}