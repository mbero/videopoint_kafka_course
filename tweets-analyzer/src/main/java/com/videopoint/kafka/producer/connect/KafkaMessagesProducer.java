package com.videopoint.kafka.producer.connect;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.videopoint.kafka.Commons;

public class KafkaMessagesProducer {

	public static KafkaProducer<String, String> getConfiguredKafkaProducer(){
		Map<String, String> producerSettings = new HashMap<String, String>();
		producerSettings.put("bootstrap.servers", "localhost:9092");
		producerSettings.put("acks", "all");
		producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerSettings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(
				Commons.getProperties(producerSettings));
		
		return kafkaProducer;
	}
	
	
	public static void produceMessage(KafkaProducer<String,String> kafkaProducer, String topicName, String message) {
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, message);
		kafkaProducer.send(producerRecord);
		System.out.println(message);
	}
	
}
