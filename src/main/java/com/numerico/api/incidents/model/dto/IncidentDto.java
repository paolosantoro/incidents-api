package com.numerico.api.incidents.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentDto {

	@NotNull
	@JsonProperty("type")
	private Type type;
	
	@NotNull
	@Valid
	@JsonProperty("properties")
	private IncidentInfo incidentInfo;
	
	@NotNull
	@JsonProperty("geometry")
	private Point geometry;

	@JsonCreator
	public IncidentDto(
			@NotNull @JsonProperty("type") Type type, 
			@Valid @JsonProperty("properties") IncidentInfo incidentInfo, 
			@JsonProperty("geometry")Point geometry
			) {
		this.type = type;
		this.incidentInfo = incidentInfo;
		this.geometry = geometry;
	}

	public Type getType() {
		return type;
	}

	public IncidentInfo getIncidentInfo() {
		return incidentInfo;
	}

	public Point getGeometry() {
		return geometry;
	}

	@Override
	public String toString() {
		return "Incident2 [type=" + type + ", incidentInfo=" + incidentInfo + ", geometry=" + geometry + "]";
	}
	
	
}
