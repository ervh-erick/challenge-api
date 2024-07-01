package com.erick.challenge.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.CarDTO;
import com.erick.challenge.api.repositories.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {

	CarRepository carRepository;
	UserService userService;

	public Car create(CarDTO objDTO) {
		objDTO.setId(null);
		objDTO.setUser(userService.findById(userService.getIdUserByContext()));
		Car newCar = new Car(objDTO);
		return carRepository.save(newCar);
	}
	
	public List<Car> findAll() {
		return carRepository.findCarByUserId(userService.getIdUserByContext());
	}

	public Car findById(UUID id) {
		Optional<Car> obj = carRepository.findByIdAndByUserId(id, userService.getIdUserByContext());
		return obj.get();
	}

	
}
