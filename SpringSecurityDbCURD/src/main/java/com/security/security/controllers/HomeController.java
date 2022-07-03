package com.security.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.security.entity.UserEmp;
import com.security.security.servic.MyUserService;

@RestController
public class HomeController {
	
	@Autowired
	MyUserService service;
	
	@GetMapping("/home")
	public String sayHi()
	{
		
		return "Hi spring security using UserDetailsManager";		
	}
	
	@PostMapping("/home")
	public void sayHi(@RequestBody UserEmp user)
	{
		
		 service.addUser(user);
	}
}
