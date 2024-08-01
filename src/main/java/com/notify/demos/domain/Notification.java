package com.notify.demos.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity(name = "notification")
@Table(name = "T_Notification")
public class Notification {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private String email;
	private String description;
    private Long number;
	private Date timeReceived;
    private Date timeSent;
    private String client;
    private String interest;
    
    public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getTimeRecieved() {
		return timeReceived;
	}

	public void setTimeRecieved(Date timeRecieved) {
		this.timeReceived = timeRecieved;
	}

	public Date getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}
	
	public Notification() {};
	
	public Notification(String name, String email, String description, Long number,String interest ) { //, MultipartFile file) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.number = number;
		this.interest = interest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "Notification [Id=" + Id + ", name=" + name + ", email=" + email + ", description=" + description
				+ ", number=" + number + "]";
	}
	
	
	
	
}
