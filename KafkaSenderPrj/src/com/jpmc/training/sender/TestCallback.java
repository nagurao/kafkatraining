package com.jpmc.training.sender;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TestCallback implements Callback {
	private int i;
	public TestCallback(int i)
	{
		super();
		this.i = i;
	}

	@Override
	public void onCompletion(RecordMetadata rmd, Exception ex) {
		if (ex==null)
			System.out.println("Message with value "+i + " published in partition "+ rmd.partition()+ " at offset "+ rmd.offset());
	}
}
