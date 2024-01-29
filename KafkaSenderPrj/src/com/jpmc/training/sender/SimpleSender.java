package com.jpmc.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleSender {
	public static void main(String args[])
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	    String topic = "first-topic";
		KafkaProducer<String, String> producer=new KafkaProducer<>(props);
	    for(int i=1;i<=20;i++) {
	        ProducerRecord<String, String> record=
	                new ProducerRecord<>(topic,"This is  test message "+i);
	        producer.send(record);
	    }
	    producer.close();
	    System.out.println("messages sent");
	}

}
