package com.erick.challenge.api.repositories;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.erick.challenge.api.domain.model.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Should get User by login successfully from DB")
	public void findUserByLoginSuccess() {
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
				.phone("999999999").build();
		this.entityManager.persist(newUser);

		//act
		Optional<User> result = this.userRepository.findByLogin(login);
		
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
	
	
	@Test
	@DisplayName("Should get User by email successfully from DB")
	public void findUserByEmailSuccess() {
		//arrange
		String email = "fulano@email.com";
		User newUser = new User().builder()
				.firstName("teste")
				.lastName("teste")
				.login("fulano")
				.email(email)
				.birthday(LocalDate.now())
				.lastLogin(LocalDate.now())
				.createdAt(LocalDate.now())
				.password("teste")
				.phone("999999999").build();
		this.entityManager.persist(newUser);

		//act
		Optional<User> result = this.userRepository.findByEmail(email);
		
		//assert
		assertThat(result.isPresent()).isTrue();

	}

	@Test
	@DisplayName("Shouldn't get user by email successfully from database")
	public void findUserByEmailFail() {
		//arrange
		String email = "fulano@email.com";
		
		//act
		Optional<User> result = this.userRepository.findByEmail(email);
		
		//assert
		assertThat(result.isEmpty()).isTrue();
	}
}
