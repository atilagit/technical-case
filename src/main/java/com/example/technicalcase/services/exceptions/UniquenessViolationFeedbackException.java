package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class UniquenessViolationFeedbackException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String uniqueness_violation_message = "a user cannot give feedback more than once for the same course";

	public UniquenessViolationFeedbackException() {
		super(uniqueness_violation_message);
	}
}