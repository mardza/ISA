package com.isa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.dto.LoginDTO;
import com.isa.entity.User;
import com.isa.security.CustomUserDetailsService;
import com.isa.security.TokenHelper;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@Lazy // iz nekog razloga circular dependency greska koja se resava sa @Lazy
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	@PostMapping(path = "/login")
	public ResponseEntity<String> createAuthenticationToken(@Valid @RequestBody LoginDTO loginDTO){
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
			System.out.println("/auth/login | User " + loginDTO.getEmail() + " successfully logged in");
		} catch (AuthenticationException e) {
			System.out.println("/auth/login | Someone tried to log in with wrong credentials");
			return new ResponseEntity<String>("Wrong email/password", HttpStatus.UNAUTHORIZED);
			// throw new BadLoginException
		}
		
		// Ubaci email + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		String jwt = tokenHelper.generateToken(user.getEmail());
		
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}

	
	// TODO: remove this method
	@GetMapping(path = "/toHash")
	public ResponseEntity<String> passwordToHash(@RequestParam("password") String password) {
		return new ResponseEntity<String>(passwordEncoder.encode(password), HttpStatus.OK);
	}
}
