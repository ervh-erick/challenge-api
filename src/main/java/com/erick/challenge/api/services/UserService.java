package com.erick.challenge.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	UserRepository userRepository;

	public User create(UserDTO objDTO) {
		objDTO.setId(null);
		objDTO.setCreatedAt(LocalDate.now());
		User newUser = new User(objDTO);
		return userRepository.save(newUser);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(UUID id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.get();
	}

}
