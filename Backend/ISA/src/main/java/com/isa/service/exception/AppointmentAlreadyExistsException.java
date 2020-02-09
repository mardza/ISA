package com.isa.service.exception;

@SuppressWarnings("serial")
public class AppointmentAlreadyExistsException extends RuntimeException {

	public AppointmentAlreadyExistsException(String message) {
		super(message);
	}
}
