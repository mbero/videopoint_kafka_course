package com.videopoint.kafka;

import com.twitter.hbc.httpclient.auth.Authentication;
import com.videopoint.kafka.authentication.AuthenticationProvider;

public class Start {

	/*
	 * TODO 
	 * 1.Twitter API - autentykacja, tworzenie po³¹czenia 
	 * 2.Pobieranie tweetów oznaczonych odpowiednimi tagami 
	 * 3.Zbudowanie obiektu posiadaj¹cego informacje o tekœcie tweetu i powi¹zanymi z nim tagami 
	 * 4.Wys³anie odpowiedniego JSONa na zadany topic
	 */

	public static void main(String[] args) {

		Authentication authentication = getAuthentication();

	}

	private static Authentication getAuthentication() {
		Authentication authentication = null;
		try {
			authentication = AuthenticationProvider.getTwitterAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authentication;
	}

}
