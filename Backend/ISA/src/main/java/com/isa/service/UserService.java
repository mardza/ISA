package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.UserDTO;
import com.isa.entity.Registration;
import com.isa.entity.Role;
import com.isa.entity.User;
import com.isa.repository.UserRepository;
import com.isa.service.exception.RegistrationAlreadyActivatedException;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private MailService mailService;
	

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findById(Integer userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new EntityNotFoundException("User with id '" + userId + "' not found");
		}
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User register(UserDTO userDTO) {
		User user = new User(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = this.roleService.findByName("ROLE_PATIENT");
		user.setRole(role);
		Registration registration = new Registration();
		this.registrationService.save(registration);
		user.setRegistration(registration);
		user = userRepository.save(user);
		return user;
	}
	
	@Override
	public List<User> findAllUnapproved() {
		List<User> userList = this.userRepository.findByRegistrationApproved(false);
		return userList;
	}
	
	@Override
	public Registration approveRegistration(String registrationId) {
		Registration registration = this.registrationService.findById(registrationId);
		registration.setApproved(true);
		registration = this.registrationService.save(registration);
		User user = this.userRepository.findByRegistrationId(registrationId);
		this.mailService.sendMail(user.getEmail(), "Activate account", "Dear " + user.getFirstName() + " " + user.getLastName() + ",\nplease activate your account by following this link: http://localhost:4200/activate-account/" + registrationId);
		return registration;
	}
	
	@Override
	public Registration activateRegistration(String registrationId) {
		Registration registration = this.registrationService.findById(registrationId);
		if(registration.getActivated()) {
			throw new RegistrationAlreadyActivatedException("User account already activated.");
		}
		registration.setActivated(true);
		registration = this.registrationService.save(registration);
		return registration;
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

}
