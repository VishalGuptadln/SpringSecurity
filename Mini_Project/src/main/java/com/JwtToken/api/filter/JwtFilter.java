package com.JwtToken.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JwtToken.api.repository.BlacklistRepository;
import com.JwtToken.api.service.CustomUserDetailsService;
import com.JwtToken.api.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BlacklistRepository blacklistRepository;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;

		try {
			//checking the token is in right format or not 
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				
				//checking the jwt token is active or inactive
				if (blacklistRepository.findByToken(token) != null) {
					throw new Exception();
				}
			}
			try {
			    userName = jwtUtil.getUsernameFromToken(token);
			}
			catch(Exception e)
			{
				
			}

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
				//checking the token is valid and authenticate token
				if (jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					UsernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			response.setStatus(401);
			
		}

	}

}
