package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.UpdateUserDTO;
import com.isa.dto.UserDTO;
import com.isa.entity.Registration;
import com.isa.entity.Role;
import com.isa.entity.User;
import com.isa.repository.UserRepository;
import com.isa.security.Util;
import com.isa.service.exception.RegistrationActivatedException;
import com.isa.service.exception.RegistrationApprovedException;

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
	
	@Autowired
	private Util util;
	

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
	public List<User> findAllPatients() {
		List<User> userList = this.userRepository.findByRoleName("ROLE_PATIENT");
		return userList;
	}
	
	@Override
	public List<User> findFiltered(Boolean approved, Boolean activated, String roleName, String firstName, String lastName,
			String insuranceNumber) {
		
//		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
//		User exampleUser = new User();
//		Registration exampleRegistration = new Registration();
//		Role exampleRole = new Role();
//		exampleRegistration.setApproved(approved);
//		exampleRegistration.setActivated(activated);
//		exampleRole.setName(roleName);
//		exampleUser.setRegistration(exampleRegistration);
//		exampleUser.setRole(exampleRole);
//		exampleUser.setFirstName(firstName);
//		exampleUser.setLastName(lastName);
//		exampleUser.setInsuranceNumber(insuranceNumber);
//		Example<User> exampleQuery = Example.of(exampleUser, matcher);
//		System.out.println(exampleQuery);
//		List<User> userList = userRepository.findAll(exampleQuery);
		
		List<User> userList = userRepository.findFiltered(approved, activated, roleName, firstName, lastName, insuranceNumber);
		return userList;
	}
	
	@Override
	public Registration approveRegistration(String email) {
		User user = this.userRepository.findByEmail(email);
		Registration registration = user.getRegistration();
		if(registration.getApproved()) {
			throw new RegistrationApprovedException("User account registration already approved");
		}
		registration.setApproved(true);
		registration = this.registrationService.save(registration);
		this.mailService.sendMail(user.getEmail(), "Activate account", "Dear " + user.getFirstName() + " " + user.getLastName() + ",\nplease activate your account by following this link: http://localhost:4200/activate-account/" + registration.getId());
		return registration;
	}
	
	@Override
	public void rejectRegistration(String email, String message) {
		User user = this.userRepository.findByEmail(email);
		this.remove(user);
		this.mailService.sendMail(user.getEmail(), "Account registration rejected", "Dear " + user.getFirstName() + " " + user.getLastName() + ",\nWe are sorry, but your account was not approved.\nMessage: " + message);
	}
	
	@Override
	public Registration activateRegistration(String registrationId) {
		Registration registration = this.registrationService.findById(registrationId);
		if(!registration.getApproved()) {
			throw new RegistrationApprovedException("User account registration not yet approved");
		}
		if(registration.getActivated()) {
			throw new RegistrationActivatedException("User account already activated.");
		}
		registration.setActivated(true);
		registration = this.registrationService.save(registration);
		return registration;
	}
	
	@Override
	public User update(String email, UpdateUserDTO updateUserDTO) {
		User user = this.userRepository.findByEmail(email);
		if(user == null) {
			throw new EntityNotFoundException("User with email " + email + " not found.");
		}
		user.setFirstName(updateUserDTO.getFirstName());
		user.setLastName(updateUserDTO.getLastName());
		user.setAddress(updateUserDTO.getAddress());
		user.setCity(updateUserDTO.getCity());
		user.setCountry(updateUserDTO.getCountry());
		user.setPhone(updateUserDTO.getPhone());
		user = save(user);
		return user;
	}
	
	@Override
	public void changePassword(String password) {
		User user = util.getCurrentUser();
		user.setPassword(passwordEncoder.encode(password));
		save(user);		
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

}
