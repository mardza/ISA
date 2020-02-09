package com.isa.controller.exception.custom;

@SuppressWarnings("serial")
public class BadLoginException extends RuntimeException {

	public BadLoginException(String message) {
		super(message);
	}
}
