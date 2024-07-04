package com.erick.challenge.api.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public RecordNotFoundException(String messsage, HttpStatus status) {
		super(messsage);
		this.status = status;
	}
	
	public RecordNotFoundException(String messsage) {
		super(messsage);
		this.status = HttpStatus.NOT_FOUND;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
