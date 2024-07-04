package com.erick.challenge.api.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.createdAt = user.getCreatedAt();
		this.lastLogin = user.getLastLogin();
	}

	public UserDTO(User user, List<Car> cars) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.createdAt = user.getCreatedAt();
		this.lastLogin = user.getLastLogin();
		this.cars = cars;
	}
	public UserDTO(UUID id, String login, String firstName, String lastName) {
		super();
		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
	}
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

}
