package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class AlreadyInactiveStatusException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String IS_ALREADY_INACTIVE_MESSAGE = "the current status is already inactive";

	public AlreadyInactiveStatusException() {
		super(IS_ALREADY_INACTIVE_MESSAGE);
	}
}