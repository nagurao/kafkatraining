package com.jpmc.training.receiver;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ReceiverFromListOfPartitionAndOffset {
public static void main(String[] args) {
    Properties props=new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    
    //int partitionToConsume=Integer.parseInt(args[0]);
    //int offset = Integer.parseInt(args[1]);
    
    KafkaConsumer<String, String> consumer=new KafkaConsumer<>(props);
    TopicPartition partitionZero=new TopicPartition("first-topic", 0);
    TopicPartition partitionOne=new TopicPartition("first-topic", 1);
    TopicPartition partitionTwo=new TopicPartition("first-topic", 2);
    List<TopicPartition> partitionList=Arrays.asList(partitionZero,partitionOne,partitionTwo);
    consumer.assign(partitionList);
    consumer.seek(partitionOne, 500);
    consumer.seek(partitionTwo, 500);
    consumer.seek(partitionZero, 500);
    
    //System.out.println("waiting for messages from partition "+partitionToConsume+" offset "+offset);
    
    while(true) {
        ConsumerRecords<String, String> records=consumer.poll(Duration.ofSeconds(30));
        records.forEach(record->System.out.println(
                "Key:"+record.key()+"\tValue:"+record.value()+"\tOffset: "+record.offset()+"\tTime: "+new Date(record.timestamp())));
    }

}
}
