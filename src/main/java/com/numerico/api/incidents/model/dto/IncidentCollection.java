package com.numerico.api.incidents.model.dto;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentCollection {

	@JsonProperty("type")
	private final Type type;

	@JsonProperty("features")
	@Valid
	private final List<IncidentDto> features;

	@JsonCreator
	public IncidentCollection(
			@JsonProperty("type") Type type, 
			@JsonProperty("features") @Valid List<IncidentDto> features) {
		this.type = type;
		this.features = features;
	}
	
	public IncidentCollection(List<IncidentDto> features) {
		this.type = Type.FEATURE_COLLECTION;
		this.features = features;
	}

	public Type getType() {
		return type;
	}

	public List<IncidentDto> getFeatures() {
		return features;
	}
}
