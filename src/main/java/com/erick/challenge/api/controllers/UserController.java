package com.erick.challenge.api.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

	UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO objDTO) {
		User newObj = userService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
