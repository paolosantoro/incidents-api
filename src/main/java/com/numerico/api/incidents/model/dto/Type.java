package com.numerico.api.incidents.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {

	FEATURE("Feature"), 
	FEATURE_COLLECTION("FeatureCollection");

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
