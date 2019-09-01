package com.target.casestudy.model;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
	private List<UserMessage> messages = new ArrayList<>();
	
	public void add(UserMessage msg) {
		messages.add(msg);
	}
	
	public void clear() {
		messages.clear();
	}
	
	public List<UserMessage> getAll(){
		return messages;
	}
}