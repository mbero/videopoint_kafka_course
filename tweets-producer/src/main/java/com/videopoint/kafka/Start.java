package com.videopoint.kafka;

import java.util.concurrent.BlockingQueue;

import org.apache.kafka.clients.producer.KafkaProducer;

import com.twitter.hbc.httpclient.auth.Authentication;
import com.videopoint.kafka.authentication.AuthenticationProvider;
import com.videopoint.kafka.client.HBCClientProcessingInvoker;
import com.videopoint.kafka.producer.KafkaMessagesProducer;
import com.videopoint.kafka.twitter.client.model.ClientWithMessagesQueue;
import com.videopoint.kafka.twitter.client.model.TweetWithTags;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		String topicName = getTopicFromArgs(args);
		String[] tagsToTrack = getTagsFromArgs(args);
		Authentication authentication = getAuthentication();
		ClientWithMessagesQueue clientWithMessagesQueue = HBCClientProcessingInvoker
				.getClientWithMessagesQueue(authentication, tagsToTrack);
		clientWithMessagesQueue.getTweeterHBCClient().connect();

		BlockingQueue<String> messagesQueue = clientWithMessagesQueue.getMessagesQueue();
		KafkaProducer<String, String> kafkaProducer = KafkaMessagesProducer.getConfiguredKafkaProducer();
		
		for (int msgRead = 0; msgRead < Integer.MAX_VALUE; msgRead++) {
			String message = messagesQueue.take();
			TweetWithTags tweetWithTags = new TweetWithTags(message, tagsToTrack);
			String finalMessage = tweetWithTags.toString();
			KafkaMessagesProducer.produceMessage(kafkaProducer, topicName, finalMessage);
		}
		
		kafkaProducer.close();
		clientWithMessagesQueue.getTweeterHBCClient().stop();

	}

	private static String getTopicFromArgs(String[] args) {
		String topicName = null;
		for (String currentArg : args) {
			if (currentArg.contains("topic")) {
				topicName = currentArg.split("=")[1];
				break;
			}
		}
		return topicName;
	}

	private static String[] getTagsFromArgs(String[] args) {
		String[] tags = null;
		for (String currentArg : args) {
			if (currentArg.contains("tags")) {
				tags = currentArg.split("=")[1].split(",");
				break;
			}
		}
		return tags;
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
