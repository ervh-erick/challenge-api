package com.erick.challenge.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erick.challenge.api.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {
	
	UserService userService;
			
	
	
}
