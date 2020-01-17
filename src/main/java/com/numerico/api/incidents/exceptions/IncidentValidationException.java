package com.numerico.api.incidents.exceptions;

import java.util.Set;
import javax.validation.ConstraintViolation;
import com.numerico.api.incidents.model.dto.IncidentDto;

public class IncidentValidationException extends RuntimeException {

	private Set<ConstraintViolation<IncidentDto>> violations;
	
	public <T> IncidentValidationException(Set<ConstraintViolation<IncidentDto>> violations) {
		this.violations = violations;
	}

	public Set<ConstraintViolation<IncidentDto>> getViolations() {
		return violations;
	}
}
