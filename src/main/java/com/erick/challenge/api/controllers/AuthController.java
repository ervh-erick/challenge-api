package com.erick.challenge.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erick.challenge.api.config.UserAuthenticationProvider;
import com.erick.challenge.api.domain.dto.CredentialsDTO;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class AuthController {

	private final UserService userService;
	private final UserAuthenticationProvider userAuthenticationProvider;

	@PostMapping(value = "/signin")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid CredentialsDTO credentialsDto) {
		UserDTO userDto = userService.login(credentialsDto);
		userDto.setToken(userAuthenticationProvider.createToken(userDto));
		
		return ResponseEntity.ok(userDto);
	}	
	
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getUserLoggedin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDTO userDTO = userService.findByLogin(((UserDTO) auth.getPrincipal()).getLogin());
		return ResponseEntity.ok(userDTO);
	}
	
}
