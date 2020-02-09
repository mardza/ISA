package com.isa.controller.exception.custom;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException(String message) {
		super(message);
	}
}
