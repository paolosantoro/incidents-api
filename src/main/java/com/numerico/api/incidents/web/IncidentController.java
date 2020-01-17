package com.numerico.api.incidents.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numerico.api.incidents.model.dto.IncidentDto;
import com.numerico.api.incidents.model.dto.IncidentCollection;
import com.numerico.api.incidents.model.repository.IncidentRepository;
import com.numerico.api.incidents.service.IncidentService;

//@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("incidents/api/v1")
@RestController
public class IncidentController {
	
	@Autowired
	private IncidentService incidentService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
	@GetMapping(value = "/rws/incidents", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IncidentCollection> getAllIncidents() {
		
		List<IncidentDto> incidents = incidentService.findAll();
        return ResponseEntity.ok(new IncidentCollection(incidents));
    }
	
//	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
//	@GetMapping(value = "/rws/incidents/{id}", produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<Incident2> getIncident(@PathVariable("id") final String id) {
//		
//        return incidentService.findById(id)
//        		.map(ResponseEntity::ok)
//        		.orElse(ResponseEntity.notFound().build());
//    }
	
	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
	@PostMapping(value = "/rws/incidents", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> submit(@Valid @RequestBody IncidentDto incident) {
		
		String id = incident.getIncidentInfo().getIncidentUUID();
		if (incidentService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		incidentService.save(incident);
		incidentService.sendIncidentsNotification();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
	@PatchMapping(value = "/rws/incidents/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<IncidentDto> update(
			@RequestBody final Map<String, Object> updates,
			@PathVariable("id") final String id) {

		Optional<IncidentDto> updateResult = incidentService.updateById(id, updates);
		if (updateResult.isPresent()) {
			incidentService.sendIncidentsNotification();
		}
		return updateResult
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
	@DeleteMapping(value = "/rws/incidents/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteIncident(@PathVariable("id") final String id) {
		
		if (!incidentService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		incidentService.deleteById(id);
		incidentService.sendIncidentsNotification();
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ESB')")
	@DeleteMapping(value = "/rws/incidents/all", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAll() {
		
		incidentService.deleteAll();
		incidentService.sendIncidentsNotification();
		return ResponseEntity.ok().build();
	}
}
