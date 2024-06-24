package com.erick.challenge.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erick.challenge.api.domain.Car;

public interface CarRepository extends JpaRepository<Car, UUID>{

}
