package com.erick.challenge.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.challenge.api.domain.Car;

public interface CarRepository extends JpaRepository<Car, UUID>{

	Optional<List<Car>> findCarByUserId(UUID id);

}
