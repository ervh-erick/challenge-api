package com.erick.challenge.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.challenge.api.domain.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}
