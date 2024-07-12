package com.erick.challenge.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.challenge.api.domain.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByLogin(String login);

	Optional<User> findByEmail(String email);

}
