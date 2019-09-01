package com.target.casestudy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.casestudy.messaging.MessageProducer;
import com.target.casestudy.model.MessageStorage;
import com.target.casestudy.model.UserMessage;
import com.target.casestudy.service.MailService;

@RestController
public class RestAPIs {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestAPIs.class);
	
	@Autowired
	MessageProducer jmsProducer;
	
	@Autowired
	private MailService notificationService;
	
	@Autowired
	private UserMessage localMsg;
	
	@Autowired
	private MessageStorage msgStore;
	
	@PostMapping(value="/api/send")
	public UserMessage postMessage(@RequestBody UserMessage msg){
		localMsg=msg;
		jmsProducer.send(msg);
		return msg;
	}
	
	@GetMapping(value="/api/messages")
	public List<UserMessage> getAll(){
		List<UserMessage> messages = msgStore.getAll();
		return messages;
	}
	
	@DeleteMapping(value="/api/messages/clear")
	public String clearMessageStorage() {
		msgStore.clear();
		return "Message Store Cleared!";
	}
	
	@GetMapping(value="/api/notify")
	public String notifyUser(){
		try {
			notificationService.sendEmail(localMsg);
			return "Congratulations! Your mail has been send to the user.";
		} catch (MailException mailException) {
			LOG.info(mailException.getMessage());
		}
		return "Error sending notification";
	}
}
