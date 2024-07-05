package com.erick.challenge.api.services;

import java.nio.CharBuffer;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.CredentialsDTO;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.exceptions.AppGenericException;
import com.erick.challenge.api.exceptions.RecordNotFoundException;
import com.erick.challenge.api.repositories.CarRepository;
import com.erick.challenge.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	UserRepository userRepository;
	CarRepository carRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDTO create(UserDTO objDTO) {
		validateObj(objDTO);

		objDTO.setId(null);
		objDTO.setCreatedAt(LocalDate.now());
		objDTO.setPassword(passwordEncoder.encode(objDTO.getPassword()));

		return new UserDTO(userRepository.save(new User(objDTO)));
	}

	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
	}

	public User findById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id));
	}

	public UserDTO update(UUID id, UserDTO objDTO) {
		if (!objDTO.validate()) {
			throw new AppGenericException("Missing fields", HttpStatus.BAD_REQUEST);
		} else {
			return new UserDTO(userRepository.findById(id).map(userFound -> {
				userFound.setBirthday(objDTO.getBirthday());
				userFound.setEmail(objDTO.getEmail());
				userFound.setFirstName(objDTO.getEmail());
				userFound.setLastName(objDTO.getLastName());
				userFound.setLogin(objDTO.getLogin());
				userFound.setPhone(objDTO.getPhone());
				userFound.setPassword(passwordEncoder.encode(objDTO.getPassword()));
				return userRepository.save(userFound);
			}).orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
		}

	}

	public void delete(UUID id) {
		userRepository.delete(userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}

	public UserDTO findByLogin(String login) {
		return new UserDTO(userRepository.findByLogin(login).orElseThrow(
				() -> new AppGenericException("Record not found by login: " + login, HttpStatus.NOT_FOUND)));
	}

	public UserDTO login(CredentialsDTO credentialsDto) {
		return userRepository.findByLogin(credentialsDto.login()).map(userFound -> {
			if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), userFound.getPassword()))
				userFound.setLastLogin(LocalDate.now());
			return new UserDTO(userRepository.save(userFound));
		}).orElseThrow(() -> new AppGenericException("Invalid login or password", HttpStatus.NOT_FOUND));
	}

	public UUID getIdUserByContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof User) {
			return ((User) auth.getPrincipal()).getId();
		}else if (auth.getPrincipal() instanceof UserDTO) {
			return ((UserDTO) auth.getPrincipal()).getId();
		}
		return null;
	}
	
	public User getUserByContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof User) {
			return userRepository.findById(((User) auth.getPrincipal()).getId()).get();
		}else if (auth.getPrincipal() instanceof UserDTO) {
			return userRepository.findById(((UserDTO) auth.getPrincipal()).getId()).get();
		}
		return null;
	}

	public UserDTO getMe(String login) {
		UserDTO userDTO = new UserDTO(userRepository.findByLogin(login).get());
		userDTO.setCars(carRepository.findCarByUserId(userDTO.getId()));
		return userDTO;
	}

	private void validateObj(UserDTO objDTO) {
		if (!objDTO.validate()) {
			throw new AppGenericException("Missing fields", HttpStatus.BAD_REQUEST);
		} else if (!userRepository.findByLogin(objDTO.getLogin()).isEmpty()) {
			throw new AppGenericException("Login already exists", HttpStatus.BAD_REQUEST);
		} else if (!userRepository.findByEmail(objDTO.getEmail()).isEmpty()) {
			throw new AppGenericException("Email already exists", HttpStatus.BAD_REQUEST);
		}
		// TODO: throw new AppException("Invalid fields", HttpStatus.BAD_REQUEST);

	}

}
