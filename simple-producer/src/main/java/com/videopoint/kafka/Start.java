package com.videopoint.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Start {

	public static void main(String[] args) {
		String topicName = "my-test-topic";
	     
	     
		 Map<String,String> producerSettings = new HashMap<String, String>();
		 producerSettings.put("bootstrap.servers", "localhost:9092");
		 producerSettings.put("acks", "all");
		 producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 producerSettings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");		 
		 
		KafkaProducer kafkaProducer = new KafkaProducer(Commons.getProperties(producerSettings));
		
		 for(int i=0; i<1000; i++) {
			 ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, String.valueOf("Message number" + i));
			 kafkaProducer.send(producerRecord);
			 System.out.println("Message send successfully");
				
		 }
		 
		 //TODO - put this into second thread
		 kafkaProducer.close();

	}

}
