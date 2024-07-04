package com.erick.challenge.api.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.erick.challenge.api.domain.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

	public User(UserDTO objDTO) {
		this.id = objDTO.getId();
		this.firstName = objDTO.getFirstName();
		this.lastName = objDTO.getLastName();
		this.email = objDTO.getEmail();
		this.birthday = objDTO.getBirthday();
		this.login = objDTO.getLogin();
		this.password = objDTO.getPassword();
		this.phone = objDTO.getPhone();
		this.createdAt = objDTO.getCreatedAt();
		this.lastLogin = objDTO.getLastLogin();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;

	@Column(name = "last_login")
	private LocalDate lastLogin;
}