package com.numerico.api.incidents.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.numerico.api.incidents.model.converter.IncidentDtoToIncidentConverter;
import com.numerico.api.incidents.model.converter.IncidentToIncidentDtoConverter;

@EnableScheduling
@EnableCaching
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	@Primary
	public ObjectMapper serializingObjectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    JavaTimeModule javaTimeModule = new JavaTimeModule();
	    javaTimeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
	    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
	    objectMapper.registerModule(javaTimeModule);
	    return objectMapper;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {

		registry.addConverter(new IncidentToIncidentDtoConverter());
		registry.addConverter(new IncidentDtoToIncidentConverter());
	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//
//		registry.addMapping("/incidents/api/v1/**")
//		.allowedOrigins("*")
//		.allowedHeaders("*")
//		.allowedMethods("*");
//	}
}
