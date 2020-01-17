package com.numerico.api.incidents.model.dto;

import java.io.Serializable;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Point implements Serializable {
	
	private static final long serialVersionUID = -3571818478868057460L;

	public enum Type {
		
		POINT("Point");

		private String value;

		Type(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return value;
		}

		@JsonCreator
		public static Type fromValue(String text) {
			for (Type type : Type.values()) {
				if (type.value.equals(text)) {
					return type;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private final Type type;

	@JsonProperty("coordinates")
	@Valid
	private final Point2D coordinates;

	public Point(@JsonProperty("coordinates") @Valid Point2D coordinates,
				 @JsonProperty("type") Type type) {
		this.type = type;
		this.coordinates = coordinates;
	}

	public Type getType() {
		return type;
	}

	public Point2D getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		return "Point [type=" + type + ", coordinates=" + coordinates + "]";
	}
}
