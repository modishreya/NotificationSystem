package com.target.casestudy.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.target.casestudy.model.UserMessage;

@Component
public class MessageProducer {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${activemq.queue}")
	String queue;
	
	public void send(UserMessage msg){
		LOG.info("Sending message on the Queue");
		jmsTemplate.convertAndSend(queue, msg);
		
	}

}
