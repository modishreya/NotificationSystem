package com.target.casestudy.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String to;
	String from;
	String subject;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	@Override
	public String toString() {
		return "UserMessage [to=" + to + ", from=" + from + ", subject=" + subject + "]";
	}
	
}
