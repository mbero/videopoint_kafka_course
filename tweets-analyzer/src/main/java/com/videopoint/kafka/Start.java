package com.videopoint.kafka;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.parser.ParseException;

import com.videopoint.kafka.analyzer.TextAnalyzer;
import com.videopoint.kafka.consumer.KafkaMessagesConsumer;

public class Start {

	public static void main(String[] args) throws ParseException {
		String inputTopic = getTopicFromArgs(args, "input_topic");
		String outputTopic = getTopicFromArgs(args, "output_topic");
		/*
		 * 1.Implementacja konsumenta (zrobione)
		 * 2.Implementacja mechanizmu analizuj�cego tekst tweetu (pod k�tem j�zyka) (zrobione)
		 * 3.Implementacja mechanizmu analizuj�cego sentyment tekstu tweetu (nacechowanie emocjonalne) 
		 * 4.Konwersja obiektu zawieraj�cego informacje o: tek�cie tweetu, warto�ci sentymentu, powi�zanych tagach
		 * do JSONA w schemacie obs�ugiwanym przez Kafka Connect
		 * 5.Wysy�anie wiadomosci na topic
		 */
		
		KafkaConsumer<String,String> kafkaConsumer = KafkaMessagesConsumer.getConfiguredKafkaConsumer();
		kafkaConsumer.subscribe(Arrays.asList(inputTopic));
		TextAnalyzer textAnalyzer = new TextAnalyzer();
		
		while(true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record: records) {
				String wholeJsonMessage = record.value();
				String wholeTweet = textAnalyzer.getGivenPropertyFromJsonString(wholeJsonMessage, "tweet");
				String tweetText = textAnalyzer.getGivenPropertyFromJsonString(wholeTweet, "text");
				if(textAnalyzer.getLanguageOfGivenText(tweetText).equals("en")) {
					System.out.println("");
				}
			
				System.out.println(wholeJsonMessage);
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
