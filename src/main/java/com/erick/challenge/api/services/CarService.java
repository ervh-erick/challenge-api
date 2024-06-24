package com.erick.challenge.api.services;

import org.springframework.stereotype.Service;

import com.erick.challenge.api.repositories.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {
	
	CarRepository carRepository;
	

}
