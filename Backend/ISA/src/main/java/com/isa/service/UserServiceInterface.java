package com.isa.service;

import java.util.List;

import com.isa.dto.UserDTO;
import com.isa.entity.Registration;
import com.isa.entity.User;

public interface UserServiceInterface {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findById(Integer userId);
	
	List<User> findAllUnapproved();
	
	Registration approveRegistration(String registrationId);
	
	Registration activateRegistration(String registrationId);
		
	User save(User user);
	
	User register(UserDTO userDTO);
	
	void remove(User user);

}
