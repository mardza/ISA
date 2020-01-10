package com.isa.service;

import java.util.List;

import com.isa.dto.UserDTO;
import com.isa.entity.User;

public interface UserServiceInterface {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findById(Integer userId);
	
	User save(User user);
	
	User register(UserDTO userDTO);
	
	void remove(User user);

}
