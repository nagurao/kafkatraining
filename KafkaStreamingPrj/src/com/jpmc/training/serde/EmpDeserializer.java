package com.jpmc.training.serde;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.training.domain.Emp;

public class EmpDeserializer implements Deserializer<Emp> {
    private ObjectMapper mapper=new ObjectMapper();
    @Override
    public Emp deserialize(String topic, byte[] array) {
        // TODO Auto-generated method stub
        Emp employee=null;
        try {
            employee=mapper.readValue(array, Emp.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return employee;
    }

}