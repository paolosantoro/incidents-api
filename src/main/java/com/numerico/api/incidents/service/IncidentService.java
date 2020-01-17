package com.numerico.api.incidents.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.numerico.api.incidents.model.repository.IncidentRepository;
import com.numerico.api.incidents.exceptions.IncidentValidationException;
import com.numerico.api.incidents.model.converter.IncidentMapper;
import com.numerico.api.incidents.model.dto.IncidentCollection;
import com.numerico.api.incidents.model.dto.IncidentDto;
import com.numerico.api.incidents.model.redis.Incident;

@Service
public class IncidentService {
	
	private static final Logger log = LoggerFactory.getLogger(IncidentService.class);
	
	private static final String RWS_INCIDENTS_TOPIC = "rws_incidents_topic";

	private final ConversionService conversionService;
	
	private final IncidentMapper incidentMapper;
	
	private final IncidentRepository incidentRepository;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	private final ObjectMapper objectMapper;
	
	private final Validator validator;
	
	@Autowired
	public IncidentService(
			ConversionService conversionService, 
			IncidentMapper incidentMapper,
			IncidentRepository incidentRepository,
			KafkaTemplate<String, String> kafkaTemplate,
			ObjectMapper objectMapper) {
		this.conversionService = conversionService;
		this.incidentMapper = incidentMapper;
		this.incidentRepository = incidentRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		this.validator = validatorFactory.getValidator();
	}
	
	public boolean existsById(String id) {
		
		return incidentRepository.existsById(id);
	}
	
	public void save(IncidentDto incidentDto) {
		
		Incident incident = conversionService.convert(incidentDto, Incident.class);
		incidentRepository.save(incident);
	}
	
	public Optional<IncidentDto> findById(String id) {
		
		return incidentRepository.findById(id)
				.map(incident -> conversionService.convert(incident, IncidentDto.class));
	}
	
	public List<IncidentDto> findAll() {
		
		return StreamSupport.stream(incidentRepository.findAll().spliterator(), false)
				.filter(Objects::nonNull)
				.map(incident -> conversionService.convert(incident, IncidentDto.class))
				.collect(Collectors.toList());
	}
	
	public Optional<IncidentDto> updateById(String id, Map<String, Object> updates) {
		
		Optional<IncidentDto> incident = findById(id);
		if (incident.isPresent()) {
			IncidentDto updateIncidentDto = incidentMapper.updateValue(incident.get(), updates);
			validate(updateIncidentDto);			
			
			Incident updateIncident = conversionService.convert(updateIncidentDto, Incident.class);
			incidentRepository.save(updateIncident);
			
			return Optional.of(updateIncidentDto);
		}
		return Optional.empty();
	}
	
	public void deleteById(String id) {
		
		incidentRepository.deleteById(id);
	}
	
	public void deleteAll() {
		
		incidentRepository.deleteAll();
	}
	
	private void validate(IncidentDto incident) {
		
		Set<ConstraintViolation<IncidentDto>> violations = validator.validate(incident);
		if (violations.size() > 0) {
			throw new IncidentValidationException(violations);
		}
	}
	
	public void sendIncidentsNotification() {
		try {
			IncidentCollection incidentCollection = new IncidentCollection(findAll());
			String incidentsMessage = objectMapper.writeValueAsString(incidentCollection);
			kafkaTemplate.send(RWS_INCIDENTS_TOPIC, incidentsMessage);
			log.info("Sending RWS Incidents notification with {} incidents", incidentCollection.getFeatures().size());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
