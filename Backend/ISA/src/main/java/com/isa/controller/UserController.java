package com.isa.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.UserDTO;
import com.isa.entity.User;
import com.isa.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAll() {
		List<User> userList = this.userService.findAll();
		List<UserDTO> userListDTO = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(userListDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") Integer id) {
		User user = this.userService.findById(id);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
		User user = this.userService.register(userDTO);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
}
