package com.jpmc.training.partitioner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import com.jpmc.training.domain.Employee;

public class EmployeePartitioner implements Partitioner{
	Properties props = new Properties();
	
	@Override
    public void configure(Map<String, ?> arg0) {
        // TODO Auto-generated method stub
        System.out.println("**********configuring**********");
        try {
        	FileInputStream fin = new FileInputStream("designation.properties");
        	props.load(fin);
        	fin.close();
        } catch (IOException e)
        {
        	e.printStackTrace();
        }
        
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        System.out.println("**********closing**********");
        props = null;
    }
    
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, 
            Object value, byte[] valueBytes, Cluster cluster) {
        // TODO Auto-generated method stub
    	int partition = 2;
    	Employee e = (Employee)value;
    	String designation = e.getDesignation();
    	if(props.containsKey(designation))
    	{
    		partition = Integer.parseInt(props.getProperty(designation));
    	}
    	System.out.println("Sending employee with id "+e.getId() + " sent to partition " + partition);
    	return partition;
    }

}
