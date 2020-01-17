package com.numerico.api.incidents.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

	@JsonProperty("errorMessages")
	private final List<String> errorMessages;
	
	@JsonProperty("statusCode")
	private final int statusCode;

	@JsonCreator
	public ErrorMessage(@JsonProperty("errorMessages") List<String> errorMessages, 
						@JsonProperty("statusCode") int statusCode) {
		this.errorMessages = errorMessages;
		this.statusCode = statusCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public int getStatusCode() {
		return statusCode;
	}	
}
