package com.erick.challenge.api.domain.dto;

import java.util.Objects;
import java.util.UUID;

import com.erick.challenge.api.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

	private UUID id;
	private int year;
	private String licensePlate;
	private String model;
	private String color;
	@JsonIgnore
	private User user;

	public boolean validate() {
		return Objects.nonNull(color) && Objects.nonNull(year) && Objects.nonNull(model)
				&& Objects.nonNull(licensePlate);
	}
}
