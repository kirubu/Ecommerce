package com.gorl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.Entity.User;


public interface UserRepo  extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailOrUsername(String email, String username);
	
	Optional<User> findByUsername(String email);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
