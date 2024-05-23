package com.practice.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.ServiceConfig;
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
	
	@GetMapping("/yaml")
	public ServiceConfig getYamlConfig(@RequestParam String name) {
		return employeeService.getDataFromYml(name);
	}

}
