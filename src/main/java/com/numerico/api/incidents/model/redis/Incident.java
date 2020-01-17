package com.numerico.api.incidents.model.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.numerico.api.incidents.model.dto.Point2D;

@RedisHash
public class Incident {

	@Id
	private String id;
	
	private String locationText;
	
	private Boolean injuries;
	
	private Boolean dangerousGoods;
	
	private String incidentType;
	
	private String centre;
	
	private String description;
	
	private String source;
	
	private Integer priority;
	
	private Long inspectorInformed;
	
	private Long inspectorAtLocation;
	
	private Long startTimestamp;
	
	private Long estimatedEndTimestamp;
	
	private String operatorUUID;

	private Point2D coordinates;

	public Incident() { }

	public Incident(String id, String locationText, Boolean injuries, Boolean dangerousGoods, String incidentType,
			String centre, String description, String source, Integer priority, Long inspectorInformed,
			Long inspectorAtLocation, Long startTimestamp, Long estimatedEndTimestamp, String operatorUUID,
			Point2D coordinates) {
		this.id = id;
		this.locationText = locationText;
		this.injuries = injuries;
		this.dangerousGoods = dangerousGoods;
		this.incidentType = incidentType;
		this.centre = centre;
		this.description = description;
		this.source = source;
		this.priority = priority;
		this.inspectorInformed = inspectorInformed;
		this.inspectorAtLocation = inspectorAtLocation;
		this.startTimestamp = startTimestamp;
		this.estimatedEndTimestamp = estimatedEndTimestamp;
		this.operatorUUID = operatorUUID;
		this.coordinates = coordinates;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocationText() {
		return locationText;
	}

	public void setLocationText(String locationText) {
		this.locationText = locationText;
	}

	public Boolean getInjuries() {
		return injuries;
	}

	public void setInjuries(Boolean injuries) {
		this.injuries = injuries;
	}

	public Boolean getDangerousGoods() {
		return dangerousGoods;
	}

	public void setDangerousGoods(Boolean dangerousGoods) {
		this.dangerousGoods = dangerousGoods;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public String getCentre() {
		return centre;
	}

	public void setCentre(String centre) {
		this.centre = centre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getInspectorInformed() {
		return inspectorInformed;
	}

	public void setInspectorInformed(Long inspectorInformed) {
		this.inspectorInformed = inspectorInformed;
	}

	public Long getInspectorAtLocation() {
		return inspectorAtLocation;
	}

	public void setInspectorAtLocation(Long inspectorAtLocation) {
		this.inspectorAtLocation = inspectorAtLocation;
	}

	public Long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Long getEstimatedEndTimestamp() {
		return estimatedEndTimestamp;
	}

	public void setEstimatedEndTimestamp(Long estimatedEndTimestamp) {
		this.estimatedEndTimestamp = estimatedEndTimestamp;
	}

	public String getOperatorUUID() {
		return operatorUUID;
	}

	public void setOperatorUUID(String operatorUUID) {
		this.operatorUUID = operatorUUID;
	}

	public Point2D getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point2D coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Incident [id=" + id + ", locationText=" + locationText + ", injuries=" + injuries + ", dangerousGoods="
				+ dangerousGoods + ", incidentType=" + incidentType + ", centre=" + centre + ", description="
				+ description + ", source=" + source + ", priority=" + priority + ", inspectorInformed="
				+ inspectorInformed + ", inspectorAtLocation=" + inspectorAtLocation + ", startTimestamp="
				+ startTimestamp + ", estimatedEndTimestamp=" + estimatedEndTimestamp + ", operatorUUID=" + operatorUUID
				+ ", coordinates=" + coordinates + "]";
	}
}
