package com.simple.api.core.util;

import static java.lang.System.getProperty;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/****
 * 
 * @author anjiboddupally
 *
 */

@Slf4j
public class ConfigLoader {

	private static ConfigLoader resourceLoader;

	private Map<String, String> propMap;

	private Properties properties;

	private final String env = getProperty("aut.env", "stg");

	private ConfigLoader() {
		properties = new Properties();

	}

	public static ConfigLoader getInstance() {
		if (resourceLoader == null) {
			return new ConfigLoader();
		}
		return resourceLoader;
	}

	public Map<String, String> getProps() {

		try {
			properties.load(new FileInputStream("src/main/resources/" + getPropfileName()));
			if(propMap == null)
				propMap = Maps.newHashMap(Maps.fromProperties(properties));
				
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return propMap;
	}

	private String getPropfileName() {
		if (env.toLowerCase().contains("stg")) {
			return "application-stg.properties";
		} else if (env.toLowerCase().contains("dev"))
			return "application-dev.properties";
		else
			return "application-stg.properties";
	}

}
