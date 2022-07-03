package com.JwtToken.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

//class user for inactive token
@Entity
public class Blacklist {
	
	@Id
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Blacklist(String token) {
		super();
		this.token = token;
	}

	Blacklist() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
