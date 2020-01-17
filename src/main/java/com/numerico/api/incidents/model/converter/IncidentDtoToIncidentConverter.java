package com.numerico.api.incidents.model.converter;

import org.springframework.core.convert.converter.Converter;

import com.numerico.api.incidents.model.dto.IncidentDto;
import com.numerico.api.incidents.model.dto.IncidentInfo;
import com.numerico.api.incidents.model.dto.Point2D;
import com.numerico.api.incidents.model.redis.Incident;

public class IncidentDtoToIncidentConverter implements Converter<IncidentDto, Incident> {

	@Override
	public Incident convert(IncidentDto incident) {
		
	    IncidentInfo incidentInfo = incident.getIncidentInfo();
		Point2D point2d = incident.getGeometry().getCoordinates();
		return new Incident(
				incidentInfo.getIncidentUUID(), 
				incidentInfo.getLocationText(), 
				incidentInfo.getInjuries(),
				incidentInfo.getDangerousGoods(),
				incidentInfo.getIncidentType(),
				incidentInfo.getCentre(),
				incidentInfo.getDescription(),
				incidentInfo.getSource(),
				incidentInfo.getPriority(),
				incidentInfo.getInspectorInformed(),
				incidentInfo.getInspectorAtLocation(),
				incidentInfo.getStartTimestamp(),
				incidentInfo.getEstimatedEndTimestamp(),
				incidentInfo.getOperatorUUID(),
				point2d);
	}
}
