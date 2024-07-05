package com.erick.challenge.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.dto.CarDTO;
import com.erick.challenge.api.exceptions.AppGenericException;
import com.erick.challenge.api.exceptions.RecordNotFoundException;
import com.erick.challenge.api.repositories.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {

	CarRepository carRepository;
	UserService userService;

	public CarDTO create(CarDTO objDTO) {

		validateObj(objDTO);

		objDTO.setId(null);
		objDTO.setUser(userService.findById(userService.getIdUserByContext()));
		return new CarDTO(carRepository.save(new Car(objDTO)));
	}

	public List<CarDTO> findAll() {
		return carRepository.findCarByUserId(userService.getIdUserByContext()).stream().map(obj -> new CarDTO(obj))
				.collect(Collectors.toList());
	}

	public Car findById(UUID id) {
		return carRepository.findByIdAndByUserId(id, userService.getIdUserByContext())
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id));
	}

	public void delete(UUID id) {
		carRepository.delete(carRepository.findByIdAndByUserId(id, userService.getIdUserByContext())
				.orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}

	public CarDTO update(UUID id, CarDTO objDTO) {
		if (!objDTO.validate())
			throw new AppGenericException("Missing fields", HttpStatus.BAD_REQUEST);
		
		return new CarDTO(carRepository.findById(id).map(carFound -> {
			carFound.setColor(objDTO.getColor());
			carFound.setModel(objDTO.getModel());
			carFound.setModel(objDTO.getModel());
			carFound.setYear(objDTO.getYear());
			return carRepository.save(carFound);
		}).orElseThrow(() -> new RecordNotFoundException("Record not found by id: " + id)));
	}

	private void validateObj(CarDTO objDTO) {
		

		if (!carRepository.findByLicensePlate(objDTO.getLicensePlate()).isEmpty())
			throw new AppGenericException("License plate already exists", HttpStatus.BAD_REQUEST);

	}
}
