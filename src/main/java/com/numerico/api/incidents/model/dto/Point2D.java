package com.numerico.api.incidents.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Point2D implements Serializable {

	private static final long serialVersionUID = -3513918306664616605L;

	private BigDecimal latitude;
	
	private BigDecimal longitude;

	public Point2D() { }
	
	@JsonCreator
	public Point2D(List<BigDecimal> coordinates) {
		this.longitude = coordinates.get(0);
		this.latitude = coordinates.get(1);
	}
	
	@JsonValue
	public BigDecimal[] toJsonPoint2D() {
		return new BigDecimal[]{ longitude, latitude };
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Point2D [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
