package com.example.technicalcase.controller.exceptions;

import com.example.technicalcase.services.exceptions.AlreadyInactiveStatusException;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(MethodArgumentNotValidException.class.getName());
		err.setMessage("Validation exception");
		err.setPath(request.getRequestURI());
		
		for (FieldError f :  e.getBindingResult().getFieldErrors()){
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ValidationError> validation(HttpMessageNotReadableException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(HttpMessageNotReadableException.class.getName());
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		err.addError(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> validation(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(HttpMessageNotReadableException.class.getName());
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AlreadyInactiveStatusException.class)
	public ResponseEntity<StandardError> validation(AlreadyInactiveStatusException e, HttpServletRequest request){
		return getStandardErrorResponseEntity(e, request, HttpStatus.CONFLICT);
	}

	private static ResponseEntity<StandardError> getStandardErrorResponseEntity(AlreadyInactiveStatusException exception, HttpServletRequest request, HttpStatus status) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(exception.getClass().getSimpleName());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}