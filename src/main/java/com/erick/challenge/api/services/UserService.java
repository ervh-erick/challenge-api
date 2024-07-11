package com.erick.challenge.api.services;

import java.nio.CharBuffer;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erick.challenge.api.config.UserAuthenticationProvider;
import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.CredentialsDTO;
import com.erick.challenge.api.domain.dto.UserDTO;
import com.erick.challenge.api.domain.mapper.UserMapper;
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
	UserMapper userMapper;

	/** Creates a new user
	 * @author Erick Silva
	 * @throws AppGenericException
	 * @param objDTO UserDTO
	 * @return CarDTO car created
	 */
	public UserDTO create(UserDTO objDTO) {
		if (!objDTO.validate()) {
			throw new AppGenericException("Missing fields", HttpStatus.BAD_REQUEST);
		} else if (!userRepository.findByLogin(objDTO.getLogin()).isEmpty()) {
			throw new AppGenericException("Login already exists", HttpStatus.BAD_REQUEST);
		} else if (!userRepository.findByEmail(objDTO.getEmail()).isEmpty()) {
			throw new AppGenericException("Email already exists", HttpStatus.BAD_REQUEST);
		}
		// TODO: throw new AppException("Invalid fields", HttpStatus.BAD_REQUEST);

		objDTO.setId(null);
		objDTO.setCreatedAt(LocalDate.now());
		objDTO.setPassword(passwordEncoder.encode(objDTO.getPassword()));

		return userMapper.toUserDTO(userRepository.save(userMapper.toUserEntity(objDTO)));

	}

	/** List all users
	 * @author Erick Silva
	 * @return List<UserDTO> list of the users
	 */
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(obj -> userMapper.toUserDTO(obj)).collect(Collectors.toList());
	}

	/** searches user that was passed as a parameter
	 * @author Erick Silva
	 * @param id UUID
	 * @throws RecordNotFoundException
	 * @return UserDTO user searched
	 */
	public UserDTO findById(UUID id) {
		return userMapper.toUserDTO(userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}
	
	/** deletes user that was passed as a parameter
	 * @author Erick Silva
	 * @throws RecordNotFoundException
	 * @param id UUID
	 * @param objDTO UserDTO
	 * @return UserDTO user updated
	 */
	public UserDTO update(UUID id, UserDTO objDTO) {
		return userMapper.toUserDTO(userRepository.findById(id).map(userFound -> {
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

	/** deletes user that was passed as a parameter
	 * @author Erick Silva
	 * @throws RecordNotFoundException
	 * @param id UUID
	 */
	public void delete(UUID id) {
		userRepository.delete(userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}

	/** searches user by login
	 * @author Erick Silva
	 * @param login String
	 * @throws AppGenericException
	 * @return UserDTO user searched
	 */
	public UserDTO findByLogin(String login) {
		return userMapper.toUserDTO(userRepository.findByLogin(login).orElseThrow(
				() -> new AppGenericException("Record not found by login: " + login, HttpStatus.NOT_FOUND)));
	}

	/** authenticates the user
	 * @author Erick Silva
	 * @throws AppGenericException
	 * @param credentialsDto CredentialsDTO
	 * @return UserDTO logged in user
	 */
	public UserDTO login(CredentialsDTO credentialsDto) {
			User user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(
				() -> new AppGenericException("Invalid login or password", HttpStatus.NOT_FOUND));

		 if (!passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
			new AppGenericException("Invalid login or password", HttpStatus.NOT_FOUND);
			return null;
		} else {
			user.setLastLogin(LocalDate.now());
			UserDTO userDTO = userMapper.toUserDTO(user);
			return userMapper.toUserDTO(userRepository.save(userMapper.toUserEntity(userDTO)));
		}
	}

	/** get user id by context
	 * @author Erick Silva
	 * @return UUID logged in user id
	 */
	public UUID getIdUserByContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof UserDTO) {
			return ((UserDTO) authentication.getPrincipal()).getId();
		}
		if (authentication.getPrincipal() instanceof User) {
			return ((User) authentication.getPrincipal()).getId();
		}
		return null;
	}

	/** get user and cars by login
	 * @author Erick Silva
	 * @param login String
	 * @return UUID logged in user id
	 */
	public UserDTO getMe(String login) {
		UserDTO userDTO = userMapper.toUserDTO(userRepository.findByLogin(login).get());
		userDTO.setCars(carRepository.findCarByUserId(userDTO.getId()));
		return userDTO;
	}
}
