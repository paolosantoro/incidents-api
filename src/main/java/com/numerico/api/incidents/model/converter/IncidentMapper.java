package com.numerico.api.incidents.model.converter;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.numerico.api.incidents.model.dto.IncidentDto;
import com.numerico.api.incidents.model.dto.IncidentInfo;
import com.numerico.api.incidents.model.dto.Point;

@Component
public class IncidentMapper {

	private final ObjectMapper objectMapper;
	
	@Autowired
	public IncidentMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@SuppressWarnings("unchecked")
	public IncidentDto updateValue(IncidentDto incidentToUpdate, Map<String, Object> overrides) {

		Map<String, Object> geometryOverride = (Map<String, Object>) overrides.get("geometry");
		Map<String, Object> propertiesOverride = (Map<String, Object>) overrides.get("properties");
		try {
			IncidentInfo updateProperties = overrides.containsKey("properties") && Objects.isNull(propertiesOverride) ?
					null : objectMapper.updateValue(incidentToUpdate.getIncidentInfo(), propertiesOverride);
			Point updateGeometry = overrides.containsKey("geometry") && Objects.isNull(geometryOverride) ?
					null : objectMapper.updateValue(incidentToUpdate.getGeometry(), geometryOverride);
			return new IncidentDto(incidentToUpdate.getType(), updateProperties, updateGeometry);
			
		} catch (JsonMappingException jme) {
			throw new RuntimeException(jme);
		}
	}
}
