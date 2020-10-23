package com.videopoint.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Start {

	public static final String INPUT_TOPIC = "streams-plaintext-input";

	public static void main(String[] args) {

		Map<String, String> producerSettings = new HashMap<String, String>();
		producerSettings.put("bootstrap.servers", "localhost:9092");
		producerSettings.put("acks", "all");
		producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerSettings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(
				Commons.getProperties(producerSettings));

		// Downloaded from https://www.learningcontainer.com/sample-text-file/
		List<String> loadedTextFile = Commons.getTextFileContent("sample-text-file.txt");
		for (int i = 0; i < loadedTextFile.size(); i++) {
			String messageContent = loadedTextFile.get(i);
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(INPUT_TOPIC, messageContent);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			kafkaProducer.send(producerRecord);
			System.out.println(messageContent);
		}
		kafkaProducer.close();

	}

}
