package com.numerico.api.incidents;

import java.time.ZoneId;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication(
		scanBasePackages = "com.numerico.api.incidents"
		,
		exclude = {SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class}
)
public class IncidentsApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Amsterdam"));
		SpringApplication.run(IncidentsApiApplication.class, args);
	}
	
	@Bean
    public ObjectMapper ObjectMapper() {
    	return new ObjectMapper();
    }
	
	@Bean
    public ZoneId defaultZoneId() {
    	return ZoneId.of("Europe/Amsterdam");
    }
}
