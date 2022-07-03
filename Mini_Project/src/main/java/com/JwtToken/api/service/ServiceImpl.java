package com.JwtToken.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.JwtToken.api.Exception.IllegalAccessException;
import com.JwtToken.api.Exception.ResourceNotFound;
import com.JwtToken.api.Exception.Unauthorized;
import com.JwtToken.api.entity.AuthRequest;
import com.JwtToken.api.entity.Blacklist;
import com.JwtToken.api.entity.ForgotPassword;
import com.JwtToken.api.entity.User;
import com.JwtToken.api.repository.BlacklistRepository;
import com.JwtToken.api.repository.UserRepository;
import com.JwtToken.api.util.JwtUtil;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlacklistRepository blacklistRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	// getting all user
	@Override
	public List<User> getAllUser() {

		List<User> list = userRepository.findAll();
		if (list.size() <= 0)
			throw new ResourceNotFound("All the feild are mendetory");
		return list;

	}

	// getting all user by id
	@Override
	public User getById(String user) {

		return this.userRepository.findById(user).orElseThrow(() -> new ResourceNotFound("User Not found"));
	}

	// update user by Admin user only
	@Override
	public String userUpdata(User user) {

		if (user.checkNull() && this.userRepository.findById(user.getUsername())
				.orElseThrow(() -> new ResourceNotFound("User Not found")) != null) {
			String enPassword = user.getPassword();
			user.setPassword(this.bCryptPasswordEncoder.encode(enPassword));
			this.userRepository.save(user);
			return "User updated successfully";
		}
		throw new IllegalAccessException("All the feild are mendetory");

	}

	// delete user ( given id)
	@Override
	public String userDelete(String username) {

		User user = userRepository.findById(username).orElseThrow(() -> new ResourceNotFound("User Not found"));
		userRepository.delete(user);
		return "User deleted successfully";

	}

	// inactive jwt token
	@Override
	public String logout(String authorizationHeader, String username) {

		String token = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
			token = authorizationHeader.substring(7);
		else
			return "Enter the valid token";

		User user = userRepository.findById(username).orElseThrow(() -> new ResourceNotFound("User Not found"));
		if (user.getUsername().equals(jwtUtil.getUsernameFromToken(token))) {
			Blacklist blacklist = new Blacklist(token);
			blacklistRepository.save(blacklist);
			return "Successfully logout";
		}
		throw new IllegalAccessException("Given token is not related with username");
	}
	
	//generating twt token
	@Override
	public String generateToken(AuthRequest authRequest) {
		try {
			//authenticate username and password
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		
		}catch(Exception e)
		{
			throw new Unauthorized("Invalid username or password");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

	// forgot password (input type username, email, password)
	@Override
	public String forgetPassword(ForgotPassword forgotPassowrd) {

		User user = userRepository.findById(forgotPassowrd.getUsername()).orElseThrow(() -> new ResourceNotFound("User Not found"));;

			if (forgotPassowrd.getEmail().equals(user.getEmail())) {
				user.setPassword(this.bCryptPasswordEncoder.encode(forgotPassowrd.getPassword()));

				userRepository.save(user);
				return "Password change successfully";
			}
		
			throw new IllegalAccessException("Email doesn't match");
	
	}

	// register new user
	@Override
	public String registerUser(User user) {
		
			if (user.checkNull() && this.userRepository.findByusername(user.getUsername())== null) {
				String enPassword = user.getPassword();
				user.setPassword(this.bCryptPasswordEncoder.encode(enPassword));
				this.userRepository.save(user);
				return "User successfully register";
			}
			throw new IllegalAccessException("All the feild are mendetory/ This user are already exist");
	}

	

}
