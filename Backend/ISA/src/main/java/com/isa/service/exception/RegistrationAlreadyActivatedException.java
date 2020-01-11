package com.isa.service.exception;

public class RegistrationAlreadyActivatedException extends RuntimeException {
	
	public RegistrationAlreadyActivatedException(String message) {
		super(message);
	}
}
