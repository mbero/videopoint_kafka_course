package com.videopoint.kafka.producer.connect;

public class Tweet {
	private String tweetText;
	private String sentimentText;
	private String tags;
	private String sentimentValue;

	public Tweet() {
		super();
	}

	public Tweet(String tweetText, String sentimentText, String tags, String sentimentValue) {
		super();
		this.tweetText = tweetText;
		this.sentimentText = sentimentText;
		this.tags = tags;
		this.sentimentValue = sentimentValue;
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public String getSentimentText() {
		return sentimentText;
	}

	public void setSentimentText(String sentimentText) {
		this.sentimentText = sentimentText;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSentimentValue() {
		return sentimentValue;
	}

	public void setSentimentValue(String sentimentValue) {
		this.sentimentValue = sentimentValue;
	}

}
