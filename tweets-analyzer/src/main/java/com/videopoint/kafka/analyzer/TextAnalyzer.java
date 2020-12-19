package com.videopoint.kafka.analyzer;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TextAnalyzer {

	private JSONParser parser;
	private org.apache.tika.language.detect.LanguageDetector detector;

	public TextAnalyzer() {
		parser = new JSONParser();
		detector = new OptimaizeLangDetector().loadModels();

	}

	public String getGivenPropertyFromJsonString(String jsonMessage, String key) throws ParseException {
		JSONObject jsonObject = (JSONObject) parser.parse(jsonMessage);
		String tweetText = (String) jsonObject.get(key);
		return tweetText;
	}

	public String[] getGivenArrayPropertyFromJSONString(String jsonMessage, String key) throws ParseException {
		JSONObject json = (JSONObject) parser.parse(jsonMessage);
		JSONArray arr = (JSONArray) json.get(key);
		Object[] objs = arr.toArray();
		String[] finalResult = Arrays.stream(objs).map(Object::toString).toArray(String[]::new);

		return finalResult;
	}

	public String getLanguageOfGivenText(String text) {
		String language = "";
		if (StringUtils.isEmpty(text) != true) {
			LanguageResult result = detector.detect(text);
			language = result.getLanguage();
		}

		return language;
	}
}
