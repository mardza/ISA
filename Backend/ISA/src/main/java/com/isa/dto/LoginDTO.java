package com.isa.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	
	public LoginDTO() {}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
