package com.example.technicalcase.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String ENTITY_NOT_FOUND_MESSAGE = "Entity not found";
	
	public ResourceNotFoundException() {
		super(ENTITY_NOT_FOUND_MESSAGE);
	}
}
