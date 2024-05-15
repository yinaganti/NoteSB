package com.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Async
	public void testAsync() {
		LOGGER.info("inside testAsync() function");
	}
}
