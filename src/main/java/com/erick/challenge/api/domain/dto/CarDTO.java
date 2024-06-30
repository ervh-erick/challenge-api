package com.erick.challenge.api.domain.dto;

import java.util.UUID;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
	public CarDTO(Car newObj) {
		this.id = newObj.getId();
		this.color = newObj.getColor();
		this.year = newObj.getYear();
		this.model = newObj.getModel();
		this.licensePlate = newObj.getLicensePlate();
	}

	private UUID id;
	private int year;
	private String licensePlate;
	private String model;
	private String color;
	@JsonIgnore
	private User user;
}
