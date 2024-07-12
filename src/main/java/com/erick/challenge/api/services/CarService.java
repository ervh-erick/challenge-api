package com.erick.challenge.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.dto.CarDTO;
import com.erick.challenge.api.domain.mapper.CarMapper;
import com.erick.challenge.api.domain.mapper.UserMapper;
import com.erick.challenge.api.domain.model.User;
import com.erick.challenge.api.exceptions.AppGenericException;
import com.erick.challenge.api.exceptions.RecordNotFoundException;
import com.erick.challenge.api.repositories.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {
	CarRepository carRepository;
	UserService userService;
	CarMapper carMapper;
	UserMapper userMapper;

	/** Creates a new car for the logged in user
	 * @author Erick Silva
	 * @throws AppGenericException
	 * @param objDTO CarDTO
	 * @return CarDTO car created
	 */
	public CarDTO create(CarDTO objDTO) {
		if (!objDTO.validate())
			throw new AppGenericException("Missing fields", HttpStatus.BAD_REQUEST);

		if (!carRepository.findByLicensePlate(objDTO.getLicensePlate()).isEmpty())
			throw new AppGenericException("License plate already exists", HttpStatus.BAD_REQUEST);

		objDTO.setId(null);
		User user = userMapper.toUserEntity(userService.findById(userService.getIdUserByContext()));
		objDTO.setUser(user);
		return carMapper.toCarDTO(carRepository.save(carMapper.toCarEntity(objDTO)));
	}

	/** List all cars for the logged in user
	 * @author Erick Silva
	 * @return List<CarDTO> list of the cars
	 */
	public List<CarDTO> findAll() {
		return carRepository.findCarByUserId(userService.getIdUserByContext()).stream()
				.map(obj -> carMapper.toCarDTO(obj)).collect(Collectors.toList());
	}

	/** searches for the logged-in user's car that was passed as a parameter
	 * @author Erick Silva
	 * @param id UUID
	 * @return CarDTO car searched
	 */
	public CarDTO findById(UUID id) {
		return carMapper.toCarDTO(carRepository.findByIdAndByUserId(id, userService.getIdUserByContext())
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}

	/** deletes the logged in user's car that was passed as a parameter
	 * @author Erick Silva
	 * @throws RecordNotFoundException
	 * @param id UUID
	 */
	public void delete(UUID id) {
		carRepository.delete(carRepository.findByIdAndByUserId(id, userService.getIdUserByContext())
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}
	
	/** update the logged in user's car that was passed as a parameter
	 * @author Erick Silva
	 * @throws RecordNotFoundException
	 * @param id UUID
	 * @param objDTO CarDTO
	 * @return CarDTO car updated
	 */
	public CarDTO update(UUID id, CarDTO objDTO) {
		return carMapper.toCarDTO(carRepository.findById(id).map(carFound -> {
			carFound.setColor(objDTO.getColor());
			carFound.setModel(objDTO.getModel());
			carFound.setModel(objDTO.getModel());
			carFound.setYear(objDTO.getYear());
			return carRepository.save(carFound);
		}).orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));

	}
}
