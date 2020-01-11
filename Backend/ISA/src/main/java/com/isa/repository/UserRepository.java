package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	List<User> findByRegistrationApproved(Boolean approved);
	
	User findByRegistrationId(String registrationId);
}
