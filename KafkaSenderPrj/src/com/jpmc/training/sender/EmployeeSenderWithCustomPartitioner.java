package com.jpmc.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.jpmc.training.domain.Employee;
import com.jpmc.training.partitioner.EmployeePartitioner;
import com.jpmc.training.serializer.EmployeeSerializer;

public class EmployeeSenderWithCustomPartitioner {
	public static void main(String a[])
	{
		Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,EmployeeSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EmployeePartitioner.class.getName());
        
        String topic="emp-topic";
        System.out.println("Starting to send messages");
        int messageCount = 0;
        KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
        for(int i=1011;i<=1020;i++) {
            Employee employee = new Employee(i,"Name "+i,"Developer");
        	ProducerRecord<String, Employee> record=
                    new ProducerRecord<>(topic,employee);
            producer.send(record);
            messageCount++;
        }
        for(int i=2011;i<=2020;i++) {
            Employee employee = new Employee(i,"Name "+i,"Tester");
        	ProducerRecord<String, Employee> record=
                    new ProducerRecord<>(topic,employee);
            producer.send(record);
            messageCount++;
        }
        for(int i=3011;i<=3020;i++) {
            Employee employee = new Employee(i,"Name "+i,"Support");
        	ProducerRecord<String, Employee> record=
                    new ProducerRecord<>(topic,employee);
            producer.send(record);
            messageCount++;
        }
        producer.close();
        System.out.println(messageCount + " messages sent");
}

}
