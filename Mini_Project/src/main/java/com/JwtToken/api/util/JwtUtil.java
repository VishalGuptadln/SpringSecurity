package com.JwtToken.api.util;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable{

	
	private static final long serialVersionUID = -8831331861137901395L;

	public static final long JWT_TOKEN_VALIDITY = 2*60*60;

	private String secret_key="vishal";

	// code to generate Token
	public String generateToken(String subject) {
		String tokenId= String.valueOf(new Random().nextInt(10000));
		return Jwts.builder()
				.setId(tokenId)
				.setSubject(subject)
				.setIssuer("ABC_Ltd")
				.setAudience("XYZ_Ltd")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret_key.getBytes()))
				.compact();
	}

	// code to get Claims
	public Claims getClaims(String token) {

		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(secret_key.getBytes()))
				.parseClaimsJws(token)
				.getBody();
	}

	// code to check if token is valid
	public boolean isValidToken(String token) {
		return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
	
	// code to check if token is valid as per username
	public boolean validateToken(String token,UserDetails userDetails) {
		String username=getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	// code to check if token is expired
	public boolean isTokenExpired(String token) {
		return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
	}
	
	//code to get expiration date
	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	//code to get username from token
	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}
}