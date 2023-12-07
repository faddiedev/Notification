package com.notify.demos.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String username;
    private String password;
    
	public User(String userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
    
    
}