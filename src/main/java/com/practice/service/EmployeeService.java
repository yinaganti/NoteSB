package com.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.practice.config.YamlConfig;
import com.practice.model.ServiceConfig;

@Service
public class EmployeeService {
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	YamlConfig yamlConfig;
	
	@Async
	public void testAsync() {
		LOGGER.info("inside testAsync() function");
	}
	
	public ServiceConfig getDataFromYml(String service) {
		return yamlConfig.getConfig(service);
	}
}
