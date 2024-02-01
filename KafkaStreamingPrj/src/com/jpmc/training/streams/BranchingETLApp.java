package com.jpmc.training.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import com.jpmc.training.domain.Emp;
import com.jpmc.training.domain.EmpOthers;
import com.jpmc.training.domain.Employee;
import com.jpmc.training.serde.EmpOthersSerde;
import com.jpmc.training.serde.EmpSerde;
import com.jpmc.training.serde.EmployeeSerde;

public class BranchingETLApp {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "branching-etl-streaming-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EmployeeSerde.class.getName());
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
        String inputTopic = "mysql-topic-branching-employee";
        String developersTopic = "developers-topic";
        String accountantsTopic = "accountants-topic";
        String othersTopic = "others-topic";
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Employee> inputStream = builder.stream(inputTopic);
        
        KStream<String, Emp> developerStream = inputStream.filter((k, v) -> v.getDesignation().equals("Developer"))
                .mapValues(e -> new Emp(e.getId(), e.getName()));
        
        KStream<String, Emp> accountantStream = inputStream
                .filter((k, v) -> v.getDesignation().equals("Accountant"))
                .mapValues(e -> new Emp(e.getId(), e.getName()));
        
        KStream<String, EmpOthers> otherStream = inputStream
                .filter((k, v) -> !(v.getDesignation().equals("Developer") || v.getDesignation().equals("Accountant")))
                .mapValues(e -> new EmpOthers(e.getId(), e.getName(), e.getDesignation()));
        
        developerStream.to(developersTopic,Produced.valueSerde(new EmpSerde()));
        accountantStream.to(accountantsTopic,Produced.valueSerde(new EmpSerde()));
        otherStream.to(othersTopic,Produced.valueSerde(new EmpOthersSerde()));
        return builder.build();
    }
}

