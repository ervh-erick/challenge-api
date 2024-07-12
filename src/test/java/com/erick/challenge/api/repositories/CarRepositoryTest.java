package com.erick.challenge.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.erick.challenge.api.domain.model.Car;
import com.erick.challenge.api.domain.model.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class CarRepositoryTest {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Should get Car by license plate successfully from DB")
	public void findCarByLicensePlateSuccess() {
		//arrange
		String login = "fulano";
		User newUser = new User().builder()
				.firstName("teste")
				.lastName("teste")
				.login(login)
				.email("email@email.com")
				.birthday(LocalDate.now())
				.lastLogin(LocalDate.now())
				.createdAt(LocalDate.now())
				.password("teste")
				.phone("999999999")
				.build();
		this.entityManager.persist(newUser);
		Optional<User> user = this.userRepository.findByLogin(login);
		UUID idUser = user.get().getId();
		
		String licensePlate = "plate";
		Car newCar = new Car().builder()
				.color("red")
				.licensePlate(licensePlate)
				.model("fusca")
				.user(newUser)
				.year(2020)
				.build();
		this.entityManager.persist(newCar);
		
		//act
		Optional<Car> result = this.carRepository.findByLicensePlate(licensePlate);

		//assert
		assertThat(result.isPresent()).isTrue();

	}

	@Test
	@DisplayName("Shouldn't get user by login successfully from database")
	public void findUserByLoginFail() {
		//arrange
		String login = "fulano";
		
		//act
		Optional<User> result = this.userRepository.findByLogin(login);
		
		//assert
		assertThat(result.isEmpty()).isTrue();
	}
}
