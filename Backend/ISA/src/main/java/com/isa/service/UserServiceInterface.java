package com.isa.service;

import java.util.List;

import com.isa.dto.UpdateUserDTO;
import com.isa.dto.UserDTO;
import com.isa.entity.Registration;
import com.isa.entity.User;

public interface UserServiceInterface {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findById(Integer userId);
	
	List<User> findAllUnapproved();
	
	List<User> findAllPatients();
	
	List<User> findFiltered(Boolean approved, Boolean activated, String roleName, String firstName, String lastName, String insuranceNumber);
	
	List<User> findByClinicAndRole(Integer clinicId, String roleName);
	
	//List<User> findPatientDoctors();
	
	Registration approveRegistration(String email);
	
	void rejectRegistration(String email, String message);
	
	Registration activateRegistration(String registrationId);
	
	List<UserDTO> findPatientDoctors();
	
	UserDTO rate(Integer doctorId, Integer rating);
		
	User save(User user);
	
	User update(String email, UpdateUserDTO updateUserDTO);
	
	void changePassword(String password);
	
	User register(UserDTO userDTO);
	
	void remove(User user);

}
