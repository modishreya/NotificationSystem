package com.target.casestudy.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.model.MessageStorage;
import com.target.casestudy.model.UserMessage;

@Component
public class MessageConsumer {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);
	
	@Autowired
	private MessageStorage messageStorage;
	
	@JmsListener(destination = "${activemq.queue}", containerFactory="jsaFactory")
	public void receive(UserMessage msg){
		
		triggerNotification(msg);
		
		LOG.info("Recieved Message: " + msg.toString());
		messageStorage.add(msg);
	}

	private void triggerNotification(UserMessage msg) {
		
		final String restEndPoint = "http://localhost:8080/api/notify";
		LOG.info("Triggering Email Notification");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = null;
		try {
			result = restTemplate.getForEntity(restEndPoint, String.class);
			LOG.info(result.toString());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
	}

}
