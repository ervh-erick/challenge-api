package com.erick.challenge.api.services;

import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.el.parser.AstListData;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.CredentialsDTO;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.exceptions.AppException;
import com.erick.challenge.api.repositories.CarRepository;
import com.erick.challenge.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	UserRepository userRepository;
	CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;

	public User create(UserDTO objDTO) {
		objDTO.setId(null);
		objDTO.setCreatedAt(LocalDate.now());
		objDTO.setPassword(passwordEncoder.encode(objDTO.getPassword()));
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

	public User update(UUID id, UserDTO objDTO) {
		objDTO.setId(id);
		User updatedObj = findById(id);
		updatedObj = new User(objDTO);
		return userRepository.save(updatedObj);
	}
	
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}

	public UserDTO findByLogin(String login) {
		User user =  userRepository.findByLogin(login).get();
		return new UserDTO(user);
	}

	public UserDTO login(CredentialsDTO credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
        	user.setLastLogin(LocalDate.now());
        	return new UserDTO(userRepository.save(user));
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
	
	public UUID getIdUserByContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth!=null ? ((UserDTO)auth.getPrincipal()).getId() : UUID.fromString("");
	}

	public UserDTO getMe(String login) {
		User user =  userRepository.findByLogin(login).get();
		UserDTO userDTO = new UserDTO(user);
		userDTO.setCars(carRepository.findCarByUserId(userDTO.getId()).get());
		return userDTO;
	}
}
