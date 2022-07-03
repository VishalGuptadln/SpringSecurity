package com.JwtToken.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JwtToken.api.entity.User;
import com.JwtToken.api.service.Service;

@RestController
@RequestMapping("/user")
public class UserModule {
	
	@Autowired
	Service service;
	
	//getting all user
	@PostMapping("/")
	public List<User> getAllUser()
	{
		return this.service.getAllUser();			
	}
	
	//getting user by id
	@PostMapping("/userId/{username}")
	public User getById(@PathVariable String username)
	{
		User user=this.service.getById(username);
		return user;
	}
	
	//update user by Admin user only (input type username, dob, mobileNO, password, email, role)
	@PostMapping("/updateUser")
	public String userUpdata(@RequestBody User user)
	{
		return this.service.userUpdata(user);
			
	}
	
	//delete user by user name (input type username)
	@PostMapping("/deleteUser/{username}")
	public String deleteUser(@PathVariable String username)
	{
		return this.service.userDelete(username);
			
	}
	
	//logout user by putting token in blacklist database and not give access while authentication token
	@PostMapping("/logout/{username}")
	public String logout(HttpServletRequest request,@PathVariable String username)
	{
		String authorizationHeader=request.getHeader("Authorization");
		
		return this.service.logout(authorizationHeader,username);
	}
	 
}
