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

import com.erick.challenge.api.domain.dto.CarDTO;
import com.erick.challenge.api.domain.model.Car;
import com.erick.challenge.api.services.CarService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/cars")
public class CarController {

	CarService carService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CarDTO create(@RequestBody @Valid CarDTO objDTO) {
		return carService.create(objDTO);
	}

	@GetMapping
	public List<CarDTO> findAll() {
		return carService.findAll();
	}

	@GetMapping(value = "/{id}")
	public CarDTO findById(@PathVariable UUID id) {
		return carService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		carService.delete(id);
	}

	@PutMapping(value = "/{id}")
	public CarDTO update(@PathVariable UUID id, @RequestBody CarDTO objDTO) {
		return carService.update(id, objDTO);
	}

}
