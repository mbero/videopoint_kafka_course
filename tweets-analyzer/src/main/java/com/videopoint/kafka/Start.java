package com.videopoint.kafka;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.json.simple.parser.ParseException;

import com.videopoint.kafka.analyzer.SentimentAnalyzer;
import com.videopoint.kafka.analyzer.TextAnalyzer;
import com.videopoint.kafka.consumer.KafkaMessagesConsumer;
import com.videopoint.kafka.model.TweetWithSentiment;
import com.videopoint.kafka.producer.connect.ConnectModel;
import com.videopoint.kafka.producer.connect.KafkaMessagesProducer;
import com.videopoint.kafka.producer.connect.ModelsTransformer;

public class Start {

	public static void main(String[] args) throws ParseException {
		String inputTopic = getTopicFromArgs(args, "input_topic");
		String outputTopic = getTopicFromArgs(args, "output_topic");
		
		KafkaProducer<String, String> kafkaProducer = KafkaMessagesProducer.getConfiguredKafkaProducer();
		KafkaConsumer<String,String> kafkaConsumer = KafkaMessagesConsumer.getConfiguredKafkaConsumer();
		kafkaConsumer.subscribe(Arrays.asList(inputTopic));
		TextAnalyzer textAnalyzer = new TextAnalyzer();
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		ModelsTransformer modelsTransformer = new ModelsTransformer();
		
		while(true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record: records) {
				String wholeJsonMessage = record.value();
				String wholeTweet = textAnalyzer.getGivenPropertyFromJsonString(wholeJsonMessage, "tweet");
				String tweetText = textAnalyzer.getGivenPropertyFromJsonString(wholeTweet, "text");
				String[] tweetTags = textAnalyzer.getGivenArrayPropertyFromJSONString(wholeJsonMessage, "tags");
				
				if(textAnalyzer.getLanguageOfGivenText(tweetText).equals("en")) {
					int mainSentiment = sentimentAnalyzer.findSentiment(tweetText);
					String sentimentText = sentimentAnalyzer.getSentimentStatusByNumber(mainSentiment).toString();
					TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(tweetText, sentimentText, tweetTags, mainSentiment);
					ConnectModel connectModel = modelsTransformer.transformTweetWithSentimentToConnectModel(tweetWithSentiment);
					String connectModelJSON = connectModel.toString();
					KafkaMessagesProducer.produceMessage(kafkaProducer, outputTopic, connectModelJSON);
				}
			
			}
		}
		
		
	}
	
	private static String getTopicFromArgs(String[] args, String argName) {
		String topicName = null;
		for (String currentArg : args) {
			if (currentArg.contains(argName)) {
				topicName = currentArg.split("=")[1];
				break;
			}
		}
		return topicName;
	}

}
