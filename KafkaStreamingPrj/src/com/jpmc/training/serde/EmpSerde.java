package com.jpmc.training.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.jpmc.training.domain.Emp;
import com.jpmc.training.domain.Employee;

public class EmpSerde implements Serde<Emp> {

    @Override
    public Deserializer<Emp> deserializer() {
        // TODO Auto-generated method stub
        return new EmpDeserializer();
    }

    @Override
    public Serializer<Emp> serializer() {
        // TODO Auto-generated method stub
        return new EmpSerializer();
    }

}