package com.numerico.api.incidents.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentInfo {
	
	@JsonProperty("locationText")
	private final String locationText;
	
	@JsonProperty("injuries")
	private final Boolean injuries;
	
	@JsonProperty("dangerousGoods")
	private final Boolean dangerousGoods;
	
	@JsonProperty("incidentType")
	private final String incidentType;
	
	@JsonProperty("centre")
	private final String centre;
	
	@JsonProperty("description")
	private final String description;
	
	@JsonProperty("source")
	private final String source;
	
	@NotBlank(message = "is required")
	@JsonProperty("incidentUUID")
	private final String incidentUUID;
	
	@JsonProperty("priority")
	private final Integer priority;
	
	@JsonProperty("inspectorInformed")
	private final Long inspectorInformed;
	
	@JsonProperty("inspectorAtLocation")
	private final Long inspectorAtLocation;
	
	@JsonProperty("startTimestamp")
	private final Long startTimestamp;
	
	@JsonProperty("estimatedEndTimestamp")
	private final Long estimatedEndTimestamp;
	
	@JsonProperty("operatorUUID")
	private final String operatorUUID;

	@JsonCreator
	public IncidentInfo(
			@JsonProperty("locationText") String locationText, 
			@JsonProperty("injuries") Boolean injuries, 
			@JsonProperty("dangerousGoods") Boolean dangerousGoods, 
			@JsonProperty("incidentType") String incidentType,
			@JsonProperty("centre") String centre, 
			@JsonProperty("description") String description, 
			@JsonProperty("source") String source, 
			@JsonProperty("incidentUUID") String incidentUUID, 
			@JsonProperty("priority") Integer priority, 
			@JsonProperty("inspectorInformed") Long inspectorInformed, 
			@JsonProperty("inspectorAtLocation") Long inspectorAtLocation, 
			@JsonProperty("startTimestamp") Long startTimestamp,
			@JsonProperty("estimatedEndTimestamp") Long estimatedEndTimestamp, 
			@JsonProperty("operatorUUID") String operatorUUID) {
		this.locationText = locationText;
		this.injuries = injuries;
		this.dangerousGoods = dangerousGoods;
		this.incidentType = incidentType;
		this.centre = centre;
		this.description = description;
		this.source = source;
		this.incidentUUID = incidentUUID;
		this.priority = priority;
		this.inspectorInformed = inspectorInformed;
		this.inspectorAtLocation = inspectorAtLocation;
		this.startTimestamp = startTimestamp;
		this.estimatedEndTimestamp = estimatedEndTimestamp;
		this.operatorUUID = operatorUUID;
	}

	public String getLocationText() {
		return locationText;
	}

	public Boolean getInjuries() {
		return injuries;
	}

	public Boolean getDangerousGoods() {
		return dangerousGoods;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public String getCentre() {
		return centre;
	}

	public String getDescription() {
		return description;
	}

	public String getSource() {
		return source;
	}

	public String getIncidentUUID() {
		return incidentUUID;
	}

	public Integer getPriority() {
		return priority;
	}

	public Long getInspectorInformed() {
		return inspectorInformed;
	}

	public Long getInspectorAtLocation() {
		return inspectorAtLocation;
	}

	public Long getStartTimestamp() {
		return startTimestamp;
	}

	public Long getEstimatedEndTimestamp() {
		return estimatedEndTimestamp;
	}

	public String getOperatorUUID() {
		return operatorUUID;
	}

	@Override
	public String toString() {
		return "IncidentInfo2 [locationText=" + locationText + ", injuries=" + injuries + ", dangerousGoods="
				+ dangerousGoods + ", incidentType=" + incidentType + ", centre=" + centre + ", description="
				+ description + ", source=" + source + ", incidentUUID=" + incidentUUID + ", priority=" + priority
				+ ", inspectorInformed=" + inspectorInformed + ", inspectorAtLocation=" + inspectorAtLocation
				+ ", startTimestamp=" + startTimestamp + ", estimatedEndTimestamp=" + estimatedEndTimestamp
				+ ", operatorUUID=" + operatorUUID + "]";
	}
}
