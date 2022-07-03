package com.JwtToken.api.entity;

//class for authenticator request to generate token 
public class AuthRequest {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	AuthRequest() {
		super();
		}
	AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
