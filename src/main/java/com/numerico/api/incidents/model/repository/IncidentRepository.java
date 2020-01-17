package com.numerico.api.incidents.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.numerico.api.incidents.model.redis.Incident;

public interface IncidentRepository extends CrudRepository<Incident, String> {
}
