package com.erick.challenge.api.services;

import org.springframework.stereotype.Service;

import com.erick.challenge.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	UserRepository userRepository;
	
}
