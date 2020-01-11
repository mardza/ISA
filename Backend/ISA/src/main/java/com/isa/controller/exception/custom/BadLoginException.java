package com.isa.controller.exception.custom;

public class BadLoginException extends RuntimeException {

	public BadLoginException(String message) {
		super(message);
	}
}
