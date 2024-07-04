package com.erick.challenge.api.exceptions;

import org.springframework.http.HttpStatus;

public class AppGenericException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	
	public AppGenericException(String message, HttpStatus status) {
    	super(message);
    	this.status = status;
    }

	public HttpStatus getStatus() {
		return status;
	}
}
