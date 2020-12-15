package com.videopoint.kafka.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private Properties properties;

	public void readProperties(String propertyFileName) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		this.properties = new Properties();
		properties.load(is);
	}

	public String getPropertyValue(String propertyName) throws Exception {
		if (this.properties == null) {
			throw new Exception(
					"Properties file not loaded. Load proper file before invoking getPropertyValue() method !");
		}
		return this.properties.getProperty(propertyName);
	}
	
	public Properties getProperties() throws Exception {
		if (this.properties == null) {
			throw new Exception(
					"Properties file not loaded. Load proper file before invoking getProperties() method !");
		}
		return this.properties;
	}
	
}
