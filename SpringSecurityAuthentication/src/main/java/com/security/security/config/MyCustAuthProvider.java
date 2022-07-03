package com.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyCustAuthProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) 
			throws AuthenticationException {
		
		
		// User input credentail
		String password=authentication.getCredentials().toString();
		
		//fetch records from in memory database
		var user=userDetailsService.loadUserByUsername(authentication.getName());
		
		if(user!=null && passwordEncoder.matches(password, user.getPassword()))
		{
			return new UsernamePasswordAuthenticationToken
			(authentication.getName(),authentication.getCredentials().toString());
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
