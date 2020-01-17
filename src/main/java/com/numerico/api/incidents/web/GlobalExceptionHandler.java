package com.numerico.api.incidents.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.numerico.api.incidents.exceptions.IncidentValidationException;
import com.numerico.api.incidents.model.dto.ErrorMessage;
import com.numerico.api.incidents.model.dto.IncidentDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> methodArgumentTypeMismatchExceptionHandler(
			MethodArgumentTypeMismatchException exception) {

		String errorDetail = Optional.ofNullable(exception.getMostSpecificCause())
								.filter(IllegalArgumentException.class::isInstance)
								.map(Throwable::getMessage)
								.map(msg -> ", " + msg)
								.orElse("");
		Map<String, Object> errorResponse = new HashMap<>();
		String errorMessage = String.format("error on parameter %s %s", exception.getName(), errorDetail);
		errorResponse.put("error", errorMessage);
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessage handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		
		List<String> errorMessages = ex.getBindingResult()
	    		.getAllErrors()
	    		.stream()
	    		.map(this::getValidationError)
	    		.collect(Collectors.toList());
	    return new ErrorMessage(errorMessages, HttpStatus.BAD_REQUEST.value());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IncidentValidationException.class)
	public ErrorMessage handleIncidentValidationException(
			IncidentValidationException ex) {
		
		List<String> errorMessages = ex.getViolations()
				.stream()
	    		.map(this::getValidationError)
	    		.collect(Collectors.toList());
	    return new ErrorMessage(errorMessages, HttpStatus.BAD_REQUEST.value());
	}
	
	private String getValidationError(ObjectError error) {
		
		String field = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
		return String.format("%s %s", field, errorMessage);
	}
	
	private String getValidationError(ConstraintViolation<IncidentDto> violation) {
		
		String propertyPath = violation.getPropertyPath().toString();
		String errorMessage = violation.getMessage();
		return String.format("%s %s", propertyPath, errorMessage);
	}
}
