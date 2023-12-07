package com.notify.demos.domain;

import org.springframework.web.multipart.MultipartFile;


public class Notification {

	private Long Id;
	private String name;
	private String email;
	private String description;
    private int number;
	
	public Notification() {};
	
	public Notification(String name, String email, String description, int number ) { //, MultipartFile file) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.number = number;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Notification [Id=" + Id + ", name=" + name + ", email=" + email + ", description=" + description
				+ ", number=" + number + "]";
	}
	
	
	
	
}
