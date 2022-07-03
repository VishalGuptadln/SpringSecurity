package com.security.security.servic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.security.entity.UserEmp;
import com.security.security.repo.LoginUser;
import com.security.security.repo.UserRepository;

public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEmp> user= repo.findByUsername(username);
		UserEmp u= user.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		return new LoginUser(u);
	}

}
