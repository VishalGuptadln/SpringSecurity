package com.security.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.security.entity.UserEmp;
import com.security.security.repo.UserRepository;

@RestController
public class HomeController {
	
	
	@GetMapping("/home")
	public String sayHi()
	{
		
		return "Hello world from db";
		
	}

}
