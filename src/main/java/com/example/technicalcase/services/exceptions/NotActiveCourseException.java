package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class NotActiveCourseException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String NOT_ACTIVE_MESSAGE = "this course is inactive";

	public NotActiveCourseException() {
		super(NOT_ACTIVE_MESSAGE);
	}
}