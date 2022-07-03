package com.JwtToken.api.service;

import java.util.List;

import com.JwtToken.api.entity.AuthRequest;
import com.JwtToken.api.entity.ForgotPassword;
import com.JwtToken.api.entity.User;

public interface Service {	
	
	public List<User> getAllUser();
	public User getById(String user);
	public String userUpdata(User user);
	public String userDelete(String username);
	public String logout(String authorizationHeader,String username);
	public String generateToken(AuthRequest authRequest);
	public String forgetPassword(ForgotPassword forgotPassowrd);
	public String registerUser(User user);
	
}