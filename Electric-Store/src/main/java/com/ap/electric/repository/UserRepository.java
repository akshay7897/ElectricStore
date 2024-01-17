package com.ap.electric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ap.electric.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByEmail(String email);
	
	Optional<List<User>> findByNameContaining(String keyword);
	
	
	
}
