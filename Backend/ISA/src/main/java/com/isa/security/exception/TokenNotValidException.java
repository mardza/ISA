package com.isa.security.exception;

@SuppressWarnings("serial")
public class TokenNotValidException extends RuntimeException {
	
	public TokenNotValidException(String message) {
		super(message);
	}
}
