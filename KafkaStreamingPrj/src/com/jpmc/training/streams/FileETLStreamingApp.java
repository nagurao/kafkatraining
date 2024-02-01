package com.jpmc.training.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

public class FileETLStreamingApp {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "file-etl-streaming-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StringSerde.class.getName());
        KafkaStreams streams=new KafkaStreams(createTopology(), props);
        streams.start();
        System.out.println("streaming started");
        try {
            Thread.sleep(10*60*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        streams.close();
        System.out.println("streaming stopped");
    }

    
    static Topology createTopology() {
        String inputTopic="file-etl-src-topic";
        String outputTopic="file-etl-sink-topic";
        StreamsBuilder builder=new StreamsBuilder();
        KStream<String, String> inputStream=builder.stream(inputTopic);
        KStream<String, String> transformedStream= inputStream.mapValues(line->{
            System.out.println("processing "+line);
            return line.toUpperCase();
        });
        transformedStream.to(outputTopic);
        return builder.build();
    }
}