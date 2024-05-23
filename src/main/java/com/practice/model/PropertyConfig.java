package com.practice.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PropertyConfig {

	@JsonProperty("api-data")
	private Map<String, ServiceConfig> apiData;
}
