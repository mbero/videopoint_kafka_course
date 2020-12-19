package com.videopoint.kafka.producer.connect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.videopoint.kafka.model.TweetWithSentiment;

public class ModelsTransformer {
	
	public ConnectModel transformTweetWithSentimentToConnectModel(TweetWithSentiment tweetWithSentiment) {
		ConnectModel connectModel = new ConnectModel();
		Schema schema = new Schema();
		schema.setType("struct");
		List<Field> fields = new ArrayList<Field>();
		Field field1 = new Field();
		field1.setType("string");
		field1.setOptional(false);
		field1.setField("tweetText");
		
		Field field2 = new Field();
		field2.setType("string");
		field2.setOptional(false);
		field2.setField("sentimentText");
		
		Field field3 = new Field();
		field3.setType("string");
		field3.setOptional(false);
		field3.setField("tags");
		
		Field field4 = new Field();
		field4.setType("string");
		field4.setOptional(false);
		field4.setField("sentimentValue");
		
		fields.add(field1);
		fields.add(field2);
		fields.add(field3);
		fields.add(field4);
		
		schema.setFields(fields);
		schema.setOptional(false);
		schema.setName("tweets_analysis_result");
		
		Tweet tweet = new Tweet();
		tweet.setSentimentText(tweetWithSentiment.getSentimentText());
		tweet.setSentimentValue(tweetWithSentiment.getSentimentValue().toString());
		String tags = Arrays.toString(tweetWithSentiment.getTags());
		tweet.setTags(tags);
		tweet.setTweetText(tweetWithSentiment.getTweetText());
		
		connectModel.setPayload(tweet);
		connectModel.setSchema(schema);
		
		
		return connectModel;
	}

}
