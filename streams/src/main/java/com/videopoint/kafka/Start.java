package com.videopoint.kafka;

import java.time.Duration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
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
		kafkaConsumer.subscribe(Arrays.asList("my-test-topic"));

		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}

	}
	

}
