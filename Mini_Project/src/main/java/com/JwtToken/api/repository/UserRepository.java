package com.JwtToken.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JwtToken.api.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	User findByusername(String username);
	User findByEmail(String email);
	User findByRole(String role);

}
