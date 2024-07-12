package com.erick.challenge.api.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.erick.challenge.api.domain.model.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String password;
	private String phone;
	private LocalDate createdAt;
	private LocalDate lastLogin;
	private List<Car> cars;
	private String token;

	public boolean validate() {
		return Objects.nonNull(birthday) && Objects.nonNull(firstName) && Objects.nonNull(email)
				&& Objects.nonNull(login) && Objects.nonNull(password) && Objects.nonNull(phone)
				&& Objects.nonNull(lastName);
	}

	public UserDTO(UUID id, String login) {
		this.id = id;
		this.login = login;
	}

}
