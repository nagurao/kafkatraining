package com.jpmc.training.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import com.jpmc.training.domain.Employee;
import com.jpmc.training.serde.EmployeeSerde;

public class MysqlToCassandraETLApp {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "mysql-cassandra-etl-streaming-app");
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
        String inputTopic="mysql-topic1-employee";
        String outputTopic="cassandra-topic-employee";
        StreamsBuilder builder=new StreamsBuilder();
        KStream<String, Employee> inputStream=builder.stream(inputTopic);
        KStream<String, Employee> transformedStream= inputStream.mapValues(emp->{
            System.out.println("processing employee with id  "+emp.getId());
            String designation=emp.getDesignation();
            Employee emp1=new Employee(emp.getId(), emp.getName(), designation);
            double salary=30000;
            if(designation.equals("Developer")) {
                salary=45000;
            }
            else if(designation.equals("Accountant")) {
                salary=35000;
            }
            else if(designation.equals("Architect")) {
                salary=70000;
            }
            emp1.setSalary(salary);
            return emp1;
        });
        transformedStream.to(outputTopic);
        return builder.build();
    }
}

