package com.videopoint.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.videopoint.kafka.Commons;

public class KafkaMessagesConsumer {

	public static KafkaConsumer<String,String> getConfiguredKafkaConsumer(){

		Map<String, String> consumerSettings = new HashMap<String, String>();
		consumerSettings.put("bootstrap.servers", "localhost:9092");
		consumerSettings.put("group.id", "test");
		consumerSettings.put("enable.auto.commit", "true");
		consumerSettings.put("auto.commit.interval.ms", "1000");
		consumerSettings.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumerSettings.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(Commons.getProperties(consumerSettings));
		
		return kafkaConsumer;
	}
}
