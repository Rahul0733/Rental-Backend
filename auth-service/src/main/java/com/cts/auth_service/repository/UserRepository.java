package com.cts.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.auth_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findUserByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
