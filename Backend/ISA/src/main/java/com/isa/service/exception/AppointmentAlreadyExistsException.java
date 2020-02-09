package com.isa.service.exception;

public class AppointmentAlreadyExistsException extends RuntimeException {

	public AppointmentAlreadyExistsException(String message) {
		super(message);
	}
}
