package com.videopoint.kafka.model;

public class TweetWithSentiment {

	private String tweetText;
	private String sentimentText;
	private String[] tags;
	private Integer sentimentValue;

	public TweetWithSentiment(String tweetText, String sentimentText, String[] tags, Integer sentimentValue) {
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer getSentimentValue() {
		return sentimentValue;
	}

	public void setSentimentValue(Integer sentimentValue) {
		this.sentimentValue = sentimentValue;
	}

}
