package com.jpmc.training.deserialzer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.training.domain.Employee;

public class EmployeeDeserializer implements Deserializer<Employee> {
    private ObjectMapper mapper=new ObjectMapper();
    @Override
    public Employee deserialize(String topic, byte[] array) {
        // TODO Auto-generated method stub
        Employee employee=null;
        try {
            employee=mapper.readValue(array, Employee.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return employee;
    }

}