package com.videopoint.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Start {

	public static void main(String[] args) {

		Map<String, String> consumerSettings = new HashMap<String, String>();
		consumerSettings.put("bootstrap.servers", "localhost:9092");
		consumerSettings.put("group.id", "test");
		consumerSettings.put("enable.auto.commit", "true");
		consumerSettings.put("auto.commit.interval.ms", "1000");
		consumerSettings.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumerSettings.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(Commons.getProperties(consumerSettings));
		kafkaConsumer.subscribe(Arrays.asList("streams-wordcount-output"));

		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records)
				System.out.println("key: " + record.key() + " |  value: " + record.value());
		}

	}

}
