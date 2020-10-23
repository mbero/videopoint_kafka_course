package com.videopoint.kafka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

	public static List<String> getTextFileContent(String textFileName) {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(Start.class.getClassLoader().getResourceAsStream(textFileName)))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
