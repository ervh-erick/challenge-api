package com.erick.challenge.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.dto.CarDTO;
import com.erick.challenge.api.services.CarService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/cars")
public class CarController {

	CarService carService;

	@PostMapping
	public ResponseEntity<CarDTO> create(@RequestBody @Valid CarDTO objDTO) {
		Car newObj = carService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(new CarDTO(newObj));
	}

	@GetMapping
	public ResponseEntity<List<CarDTO>> findAll() {
		List<Car> list = carService.findAll();
		List<CarDTO> listDTO = list.stream().map(obj -> new CarDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

}
