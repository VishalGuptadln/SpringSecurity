package com.JwtToken.api.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorDetail> ResourceNotFound(ResourceNotFound resourceNotFound)
	{
		ErrorDetail error=new ErrorDetail(resourceNotFound.getMessage(), "NP999", new Date());
		return new ResponseEntity<ErrorDetail>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<ErrorDetail> IllegalAccessException(IllegalAccessException illegalAccessException)
	{
		ErrorDetail error=new ErrorDetail(illegalAccessException.getMessage(), "NP999", new Date());
		return new ResponseEntity<ErrorDetail>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Unauthorized.class)
	public ResponseEntity<ErrorDetail> Unauthorized(Unauthorized unauthorized)
	{
		ErrorDetail error=new ErrorDetail(unauthorized.getMessage(), "NP999", new Date());
		return new ResponseEntity<ErrorDetail>(error,HttpStatus.UNAUTHORIZED);
	}

}
