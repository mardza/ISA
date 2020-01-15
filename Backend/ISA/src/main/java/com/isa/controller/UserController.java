package com.isa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.RegistrationDTO;
import com.isa.dto.UpdateUserDTO;
import com.isa.dto.UserDTO;
import com.isa.entity.Registration;
import com.isa.entity.User;
import com.isa.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	

//	@GetMapping()
//	public ResponseEntity<List<UserDTO>> getAll() {
//		List<User> userList = this.userService.findAll();
//		return new ResponseEntity<List<UserDTO>>(UserDTO.toList(userList), HttpStatus.OK);
//	}

	@GetMapping(path = "/{email}")
	public ResponseEntity<UserDTO> getByEmail(@PathVariable("email") String email) {
		User user = this.userService.findByEmail(email);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
		User user = this.userService.register(userDTO);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}

	@GetMapping(path = "/unapproved")
	public ResponseEntity<List<UserDTO>> getAllUnapproved() {
		List<User> userList = this.userService.findAllUnapproved();
		return new ResponseEntity<List<UserDTO>>(UserDTO.toList(userList, true), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAll(
			@RequestParam(name = "approved", required = false) Boolean approved,
			@RequestParam(name = "activated", required = false) Boolean activated,
			@RequestParam(name = "roleName", required = false) String roleName,
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName,
			@RequestParam(name = "insuranceNumber", required = false) String insuranceNumber){
		List<User> userList = this.userService.findFiltered(approved, activated, roleName, firstName, lastName, insuranceNumber);
		return new ResponseEntity<List<UserDTO>>(UserDTO.toList(userList, true), HttpStatus.OK);
	}

	@PostMapping(path = "/approve-registration/{email}")
	public ResponseEntity<Void> approveRegistration(@PathVariable("email") String email, @RequestParam("approved") Boolean approved, @RequestBody(required = false) String message) {
		if(approved) {
			this.userService.approveRegistration(email);
		}
		else {
			this.userService.rejectRegistration(email, message);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping(path = "/activate-registration/{id}")
	public ResponseEntity<RegistrationDTO> activateRegistration(@PathVariable("id") String id) {
		Registration registration = this.userService.activateRegistration(id);
		return new ResponseEntity<RegistrationDTO>(new RegistrationDTO(registration), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{email}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("email") String email, @RequestBody @Valid UpdateUserDTO updateUserDTO){
		this.userService.update(email, updateUserDTO);
		return new ResponseEntity<UserDTO>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/password")
	public ResponseEntity<Void> updateUserPassword(@RequestParam("password") String password){
		this.userService.changePassword(password);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
