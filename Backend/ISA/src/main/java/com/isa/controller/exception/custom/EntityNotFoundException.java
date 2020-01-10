package com.isa.controller.exception.custom;

public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException(String message) {
		super(message);
	}
}
