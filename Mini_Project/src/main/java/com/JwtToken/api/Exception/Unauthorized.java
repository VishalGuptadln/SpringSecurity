package com.JwtToken.api.Exception;

public class Unauthorized extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Unauthorized(String message) {
		super(message);
		
	}

}
