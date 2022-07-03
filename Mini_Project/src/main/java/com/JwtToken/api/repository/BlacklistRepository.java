package com.JwtToken.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JwtToken.api.entity.Blacklist;

public interface BlacklistRepository extends JpaRepository<Blacklist, String> {
	
	Object findByToken(String token);

}
