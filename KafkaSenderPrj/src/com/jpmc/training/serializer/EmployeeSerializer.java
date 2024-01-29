package com.jpmc.training.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.training.domain.Employee;

public class EmployeeSerializer implements Serializer<Employee>{

	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public byte[] serialize(String topic, Employee e) {
		// TODO Auto-generated method stub
		byte[] arr = null;
		try
		{
			arr=mapper.writeValueAsBytes(e);
		}catch (JsonProcessingException e1)
		{
			e1.printStackTrace();
		}
		System.out.println("Serialized to "+ new String(arr));
		return arr;
	}

}
