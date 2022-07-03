package com.security.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.security.security.entity.UserEmp;
import com.security.security.repo.UserRepository;
import com.security.security.servic.LoginUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		
		return new LoginUserDetailsService();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		
		return NoOpPasswordEncoder.getInstance();
	}
}
