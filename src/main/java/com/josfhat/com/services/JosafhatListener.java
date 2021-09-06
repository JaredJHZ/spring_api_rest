package com.josfhat.com.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class JosafhatListener {

	@KafkaListener(topics = "josafhat-topic", groupId = "josafhatGroup")
	public void listen(String message) {
		System.out.println("Message: "+ message);
	}
	
}
