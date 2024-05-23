package com.practice.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.practice.model.PropertyConfig;
import com.practice.model.ServiceConfig;

@Component
public class YamlConfig implements ApplicationRunner{

	private Map<String, ServiceConfig> map = new HashMap<>();
	
	String path = "src/main/resources/config";
	
	public ServiceConfig getConfig(String serviceName) {
		return map.get(serviceName);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.map = initialize();
	}

	private Map<String, ServiceConfig> initialize() {
		Map<String, ServiceConfig> map = new HashMap<>();
		Path dir = Paths.get(path);
		PropertyConfig config = null;
		try {
			Stream<Path> files = Files.list(dir);
			String fileName = files.map(Path::getFileName).map(path->path.toString()).findFirst().get();
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			config = mapper.readValue(new File(path+"/"+fileName), PropertyConfig.class);
		} catch (Exception e) {
			throw new RuntimeException("error while loading config");
		} 
		config.getApiData().forEach((key, value)->{
			map.put(key, value);
		});
		return map;
	}
}
