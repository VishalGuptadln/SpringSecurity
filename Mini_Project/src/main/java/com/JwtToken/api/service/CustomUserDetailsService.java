package com.JwtToken.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JwtToken.api.entity.User;
import com.JwtToken.api.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
	@Autowired
	private UserRepository urepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=urepository.findByusername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("could not found user!!");
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		
		return customUserDetails;
			}

}
