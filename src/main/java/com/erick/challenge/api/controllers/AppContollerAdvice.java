package com.erick.challenge.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erick.challenge.api.domain.model.ApiError;
import com.erick.challenge.api.exceptions.AppGenericException;
import com.erick.challenge.api.exceptions.RecordNotFoundException;

@RestControllerAdvice
public class AppContollerAdvice {

	@ExceptionHandler(AppGenericException.class)
	public ResponseEntity<ApiError> handleAppGenericException(AppGenericException ex) {
		ApiError apiError = ApiError.builder().message(ex.getMessage()).errorCode(ex.getStatus().value()).build();
		return new ResponseEntity<>(apiError, ex.getStatus());
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(RecordNotFoundException ex) {
		ApiError apiError = ApiError.builder().message(ex.getMessage()).errorCode(ex.getStatus().value()).build();
		return new ResponseEntity<>(apiError, ex.getStatus());
	}

}
