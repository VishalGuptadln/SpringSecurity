package com.security.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.security.security.filter.MyCustomAuthenticationToken;

@Component
public class MyAuthenticatioProvider implements AuthenticationProvider{

	@Value("${secret_key}")
	String secretKey;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		if(secretKey.equals(authentication.getName()))
		{
			return new MyCustomAuthenticationToken(null,null,null);
		}
		return null;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return MyCustomAuthenticationToken.class.equals(authentication);
	}
	
	
	
	

}
