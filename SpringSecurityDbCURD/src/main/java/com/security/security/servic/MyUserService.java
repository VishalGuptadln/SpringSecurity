package com.security.security.servic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.security.security.entity.UserEmp;

@Service
public class MyUserService {
	
	@Autowired
	UserDetailsManager manager;
	
	@Autowired
	PasswordEncoder encoder;
	
	public void addUser(UserEmp user)
	{
		//encryption 
		user.setPassword(encoder.encode(user.getPassword()));
		manager.createUser(user);
	}

}
