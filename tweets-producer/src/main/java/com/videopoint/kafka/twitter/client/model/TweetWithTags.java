package com.videopoint.kafka.twitter.client.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TweetWithTags {

	private String tweet;
	private String[] tags;
	private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	public TweetWithTags(String tweet, String[] tags) {
		super();
		this.tweet = tweet;
		this.tags = tags;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		String json = null;
		try {
			json = ow.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
