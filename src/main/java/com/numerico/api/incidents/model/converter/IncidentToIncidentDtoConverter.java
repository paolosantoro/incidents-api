package com.numerico.api.incidents.model.converter;

import org.springframework.core.convert.converter.Converter;

import com.numerico.api.incidents.model.dto.IncidentDto;
import com.numerico.api.incidents.model.dto.IncidentInfo;
import com.numerico.api.incidents.model.dto.Point;
import com.numerico.api.incidents.model.dto.Type;
import com.numerico.api.incidents.model.redis.Incident;

public class IncidentToIncidentDtoConverter implements Converter<Incident, IncidentDto> {

	@Override
	public IncidentDto convert(Incident incident) {
		
		Point point = new Point(incident.getCoordinates(), Point.Type.POINT);
		
		IncidentInfo incidentInfo = new IncidentInfo(
				incident.getLocationText(), 
				incident.getInjuries(),
				incident.getDangerousGoods(),
				incident.getIncidentType(),
				incident.getCentre(),
				incident.getDescription(),
				incident.getSource(),
				incident.getId(),
				incident.getPriority(),
				incident.getInspectorInformed(),
				incident.getInspectorAtLocation(),
				incident.getStartTimestamp(),
				incident.getEstimatedEndTimestamp(),
				incident.getOperatorUUID());
		
		return new IncidentDto(Type.FEATURE, incidentInfo, point);
	}
}
