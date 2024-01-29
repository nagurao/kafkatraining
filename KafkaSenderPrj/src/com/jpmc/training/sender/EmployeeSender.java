package com.jpmc.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.jpmc.training.domain.Employee;
import com.jpmc.training.serializer.EmployeeSerializer;

public class EmployeeSender {
	public static void main(String a[])
	{
		Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,EmployeeSerializer.class.getName());
        
        String topic="emp-topic";
        KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
        for(int i=1001;i<=1010;i++) {
            Employee employee = new Employee(i,"Name "+i,"Developer "+ i);
        	ProducerRecord<String, Employee> record=
                    new ProducerRecord<>(topic,"dev",employee);
            producer.send(record);
        }
        for(int i=2001;i<=2010;i++) {
            Employee employee = new Employee(i,"Name "+i,"Tester "+ i);
        	ProducerRecord<String, Employee> record=
                    new ProducerRecord<>(topic,"test",employee);
            producer.send(record);
        }
        producer.close();
        System.out.println("messages sent");
}

}
