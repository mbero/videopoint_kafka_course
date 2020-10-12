package com.videopoint.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Start {

	public static void main(String[] args) {
		String topicName = "my-test-topic";

		Map<String, String> producerSettings = new HashMap<String, String>();
		producerSettings.put("bootstrap.servers", "localhost:9092");
		producerSettings.put("acks", "all");
		producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerSettings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(
				Commons.getProperties(producerSettings));

		for (int i = 0; i < 1000; i++) {
			String messageContent = "Some message :" + i; 
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, messageContent);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			kafkaProducer.send(producerRecord);
			System.out.println(messageContent);
		}
		kafkaProducer.close();

	}

}
