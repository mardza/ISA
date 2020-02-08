package com.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.controller.exception.custom.EntityNotFoundException;
import com.isa.dto.UpdateUserDTO;
import com.isa.dto.UserDTO;
import com.isa.entity.Appointment;
import com.isa.entity.DoctorRating;
import com.isa.entity.Registration;
import com.isa.entity.Role;
import com.isa.entity.User;
import com.isa.repository.UserRepository;
import com.isa.security.exception.TokenNotValidException;
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
	private DoctorRatingService doctorRatingService;
	
	

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User getCurrentUser() {
		String email;
		Authentication currentUserAuth;
		currentUserAuth = SecurityContextHolder.getContext().getAuthentication();
		if(currentUserAuth != null) {
			email = currentUserAuth.getName();
			User user = this.findByEmail(email);
			if(user == null) {
				throw new TokenNotValidException("Could not get current user, Auth not valid");
			}
			return user;
		}
		throw new TokenNotValidException("Could not get current user, Auth not valid");
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
	public List<User> findByClinicAndRole(Integer clinicId, String roleName) {
		return this.userRepository.findByClinicIdAndRoleName(clinicId, roleName);
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
	public List<UserDTO> findPatientDoctors() {
		User patient = this.getCurrentUser();
		List<Appointment> patientAppointments = patient.getPatientAppointmentList();
		Long time = new Date().getTime();
		patientAppointments.removeIf(appointment -> {
			return appointment.getTime().getTime() >= time;
		});
		Set<User> doctorSet = new HashSet<User>();
		patientAppointments.forEach(appointment -> {
			doctorSet.add(appointment.getDoctor());
		});
		doctorSet.forEach(doctor -> {
			doctor.setRatingAverage(this.doctorRatingService.findDoctorRating(doctor.getId()));
		});
		List<UserDTO> doctorDTOList = UserDTO.toList(new ArrayList<User>(doctorSet));
		doctorDTOList.forEach(doctorDTO -> {
			DoctorRating doctorRating = this.doctorRatingService.findByPatientIdAndClinicId(patient.getId(), doctorDTO.getId());
			if(doctorRating != null) {
				doctorDTO.setPatientRating(doctorRating.getValue());
			}
		});
		return doctorDTOList;
	}
	
	@Override
	public UserDTO rate(Integer doctorId, Integer rating) {
		User doctor = this.findById(doctorId);
		User patient = this.getCurrentUser();
		DoctorRating doctorRating = this.doctorRatingService.findByPatientIdAndClinicId(patient.getId(), doctorId);
		if(doctorRating != null) {
			doctorRating.setValue(rating);
			doctorRating = this.doctorRatingService.update(doctorRating);
		} else {
			doctorRating = new DoctorRating();
			doctorRating.setPatient(patient);
			doctorRating.setDoctor(doctor);
			doctorRating.setValue(rating);
			doctorRating = this.doctorRatingService.create(doctorRating);
		}
		UserDTO userDTO = new UserDTO(doctor);
		userDTO.setPatientRating(rating);
		return userDTO;
	}
	
	@Override
	public void changePassword(String password) {
		User user = this.getCurrentUser();
		user.setPassword(passwordEncoder.encode(password));
		save(user);		
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

}
