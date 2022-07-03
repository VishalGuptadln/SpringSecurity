package com.security.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.security.entity.UserEmp;

public interface UserRepository extends JpaRepository<UserEmp, Integer> {
	
	Optional<UserEmp> findByUsername(String uname);

}
