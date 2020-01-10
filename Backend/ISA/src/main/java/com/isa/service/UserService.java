package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.UserDTO;
import com.isa.entity.Role;
import com.isa.entity.User;
import com.isa.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new EntityNotFoundException("User with email '" + email + "' not found");
		}
		return user;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findById(Integer userId) {
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			throw new EntityNotFoundException("User with id '" + userId + "' not found");
		}
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User register(UserDTO userDTO) {
		User user = new User(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = this.roleService.findByName("ROLE_PATIENT");
		user.setRole(role);
		user = userRepository.save(user);
		if(user == null) {
			// throw new CantRegisterNewUserException("");
		}
		return user;
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

}
