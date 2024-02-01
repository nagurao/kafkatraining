package com.jpmc.training.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.jpmc.training.domain.EmpOthers;

public class EmpOthersSerde implements Serde<EmpOthers> {

    @Override
    public Deserializer<EmpOthers> deserializer() {
        // TODO Auto-generated method stub
        return new EmpOthersDeserializer();
    }

    @Override
    public Serializer<EmpOthers> serializer() {
        // TODO Auto-generated method stub
        return new EmpOthersSerializer();
    }

}