package com.example.technicalcase.controller.exceptions;

import java.io.Serial;
import java.io.Serializable;

public record FieldMessage(String fieldName, String message) implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
}
