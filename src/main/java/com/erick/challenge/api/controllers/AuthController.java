package com.erick.challenge.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erick.challenge.api.config.UserAuthenticationProvider;
import com.erick.challenge.api.domain.dto.CredentialsDTO;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.exceptions.AppGenericException;
import com.erick.challenge.api.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final UserService userService;
	private final UserAuthenticationProvider userAuthenticationProvider;
	HttpServletResponse response;
	
	@PostMapping(value = "api/signin")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid CredentialsDTO credentialsDto) {
		UserDTO userDTO = null; 
		String sErrorMsg = "";
		
		try {
			HttpHeaders responseHeaders = new HttpHeaders();
			userDTO = userService.login(credentialsDto);
			String token = userAuthenticationProvider.createToken(userDTO);
			userDTO.setToken(token);
			
			responseHeaders.set(HttpHeaders.AUTHORIZATION, token);
			
		    return ResponseEntity.ok()
		      .headers(responseHeaders)
		      .body(userDTO);
		} catch (Exception e) {
		      sErrorMsg = "Invalid login or password";
		}
	    
	    ResponseEntity responseEntity = null;
	    if (userDTO == null) {
	        responseEntity = new ResponseEntity<>("Invalid login or password", HttpStatus.NOT_FOUND);
	    }
	    return responseEntity;
	}

	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getUserLoggedin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDTO userDTO = userService.getMe(((UserDTO) auth.getPrincipal()).getLogin());
		return ResponseEntity.ok(userDTO);
	}
}
