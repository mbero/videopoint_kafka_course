package com.videopoint.kafka;

import java.util.Map;
import java.util.Properties;

public class Commons {

	public static Properties getProperties(Map<String, String> propertiesMap) {
		Properties properties = new Properties();
		for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
			properties.put(entry.getKey(), entry.getValue());
		}
		return properties;
	}
}
