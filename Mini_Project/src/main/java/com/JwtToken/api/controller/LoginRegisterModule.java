package com.JwtToken.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JwtToken.api.entity.AuthRequest;
import com.JwtToken.api.entity.ForgotPassword;
import com.JwtToken.api.entity.User;
import com.JwtToken.api.service.Service;

@RestController
@RequestMapping("/userManage")
public class LoginRegisterModule {
	
	
	
	@Autowired
	Service service;
	
	// Generating token (input type username, password)	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest){
		 return this.service.generateToken(authRequest);
	}
	
	// forgot password (input type username, email, password)
	@PostMapping("/forgot")
	public String forgot(@RequestBody ForgotPassword forgotPassword)
	{
		return this.service.forgetPassword(forgotPassword);
	}
	
	//register new user (input type username, dob, mobileNO, password, email, role)
	@PostMapping("/register")
	public String registerUser(@RequestBody User user)
	{
		 return this.service.registerUser(user);
	}
	
	

}
