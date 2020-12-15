package com.videopoint.kafka.authentication;

import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.videopoint.kafka.properties.PropertiesLoader;

public class AuthenticationProvider {

	public static Authentication getTwitterAuthentication() throws Exception {
		PropertiesLoader propertiesLoader = new PropertiesLoader();
		propertiesLoader.readProperties("twitter.properties");
		String consumerKey = propertiesLoader.getPropertyValue("twitter.consumer.key");
		String consumerSecret= propertiesLoader.getPropertyValue("twitter.consumer.secret");
		String token = propertiesLoader.getPropertyValue("twitter.token");
		String tokenSecret = propertiesLoader.getPropertyValue("twitter.token.secret");
		
		Authentication authentication = new OAuth1(consumerKey, consumerSecret, token, tokenSecret);
		return authentication;
	}

}
