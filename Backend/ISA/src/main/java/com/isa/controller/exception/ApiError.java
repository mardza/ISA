package com.isa.controller.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiError {

	private HttpStatus status;
	private Date timestamp;
	private String message;
	private Object data;

	private ApiError() {
		this.timestamp = new Date();
	}

	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = ex.getMessage();
	}

	public void setErrors(Object errors) {
		this.data = errors;
	}
	

	public String getStatusText() {
		return this.status.name();
	}

	public Integer getStatusCode() {
		return this.status.value();
	}
	
	@JsonIgnore
	public HttpStatus getStatus() {
		return this.status;
	}

	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
