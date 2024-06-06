package com.practice.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import com.jayway.jsonpath.JsonPath;

public class JsonObjectReader {
	
	public Object getJsonValue(Object json, String jsonKey) throws ParseException {
		
		if(json instanceof List && jsonKey != null) {
			int index = jsonKey.indexOf(".");
			String tempKey = jsonKey;
			String nextKey = null;
			if(index != -1) {
				tempKey = jsonKey.substring(0, index); 
				nextKey = jsonKey.substring(index+1);
			}
			int jsonIndex = Integer.valueOf(StringUtils.substringBetween(tempKey, "[", "]"));
			List<Map<String, Object>> list = (ArrayList<Map<String,Object>>) json;
			return getJsonValue(list.get(jsonIndex), nextKey);
		}
		
		if(json instanceof Map && jsonKey != null) {
			Map<String, Object> map = (LinkedHashMap<String, Object>) json;
			int index = jsonKey.indexOf(".");
			String tempKey = jsonKey;
			String nextKey = null;
			if(index != -1) {
				tempKey = jsonKey.substring(0, index); 
				nextKey = jsonKey.substring(index+1);
			}
			if(tempKey.contains("[")) {
				String prefix = tempKey.substring(0,tempKey.indexOf("["));
				String suffix = jsonKey.substring(tempKey.indexOf("["));
				return getJsonValue(map.get(prefix), suffix);
			}
		   return getJsonValue(map.get(tempKey), nextKey);
		}
		return json;
	}
	
	public static void main(String args[]) throws ParseException {
		JsonObjectReader reader = new JsonObjectReader();
		String json = "{\r\n"
				+ "    \"emp\": {\r\n"
				+ "        \"data\":[\"sri\"]\r\n"
				+ "    },\r\n"
				+ "    \"dept\" : {\r\n"
				+ "        \"result\" : 123,\r\n"
				+ "        \"flag\" : true\r\n"
				+ "    }\r\n"
				+ "}";
		
		System.out.println(reader.getValue(json, "${dept.flag}"));
	}
	
	public Object getValue(String json, String path) {
		path = StringUtils.substringBetween(path, "${", "}");
		try {
			Object obj = new JSONParser(json).parse();
			return getJsonValue(obj, path);

		} catch (ParseException exc) {
			return null;
		}
	}
	
	/*json-path lib
	 * 
	 * to find path : $[0].employee.id
	 * https://jsonpathfinder.com/
	 */
	public static void test(String json) {
		Object obj = JsonPath.read(json, "$[0].employee.id");
		System.out.println(obj);
	}
}
