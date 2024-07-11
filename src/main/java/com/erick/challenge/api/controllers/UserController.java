package com.erick.challenge.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

	UserService userService;

	@GetMapping
	public List<UserDTO> findAll() {
		return userService.findAll();
	}

	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable UUID id) {
		return userService.findById(id);
	}

	@PutMapping(value = "/{id}")
	public UserDTO update(@PathVariable UUID id, @RequestBody UserDTO objDTO) {
		return userService.update(id, objDTO);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		 userService.delete(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDTO create(@RequestBody @Valid UserDTO objDTO) {
		return userService.create(objDTO);
	}

}
