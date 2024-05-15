package com.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/async")
	public String testAsyncFun() {
		LOGGER.info("start: EmployeeController.testAsyncFun()");
		employeeService.testAsync();
		LOGGER.info("end: EmployeeController.testAsyncFun()");
		return "Success";
	}

}
