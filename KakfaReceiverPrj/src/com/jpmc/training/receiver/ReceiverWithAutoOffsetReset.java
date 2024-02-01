package com.jpmc.training.receiver;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ReceiverWithAutoOffsetReset {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Properties props=new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-4");
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        KafkaConsumer<String, String> consumer=new KafkaConsumer<>(props);
        List<String> topicList=Collections.singletonList("first-topic");
        consumer.subscribe(topicList);
        
        System.out.println("waiting for messages");
        while(true) {
            ConsumerRecords<String, String> records=consumer.poll(Duration.ofSeconds(20));
            records.forEach(record->System.out.println(
                    "Key:"+record.key()+"\tValue:"+record.value()+"\tPartition: "+record.partition()+"\tOffset: "+record.offset()));
        }

    }

}
