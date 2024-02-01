package com.jpmc.training.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.jpmc.training.domain.Employee;

public class EmployeeSerde implements Serde<Employee> {

    @Override
    public Deserializer<Employee> deserializer() {
        // TODO Auto-generated method stub
        return new EmployeeDeserializer();
    }

    @Override
    public Serializer<Employee> serializer() {
        // TODO Auto-generated method stub
        return new EmployeeSerializer();
    }

}