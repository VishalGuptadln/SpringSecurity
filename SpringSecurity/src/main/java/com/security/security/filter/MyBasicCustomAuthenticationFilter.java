package com.security.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MyBasicCustomAuthenticationFilter implements Filter {
	
	@Autowired
	AuthenticationManager manager;

	@Override
	public void doFilter(ServletRequest req,
			ServletResponse res,
			FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest)req;		
		String authObj=request.getHeader("auth_key");
		
		//create authentication object
		var objToken=new MyCustomAuthenticationToken(authObj,null);
		try {
		var authPrincipal=manager.authenticate(objToken);
		
		if(authPrincipal.isAuthenticated())
		{
			SecurityContextHolder.getContext().setAuthentication(authPrincipal);
			chain.doFilter(req, res);
			}
		}catch(AuthenticationException e)
		{
			new BadCredentialsException("Invalid Principal");
		}
	}

}
