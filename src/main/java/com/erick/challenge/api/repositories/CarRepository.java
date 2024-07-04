package com.erick.challenge.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erick.challenge.api.domain.Car;

public interface CarRepository extends JpaRepository<Car, UUID> {

	List<Car> findCarByUserId(UUID id);

	@Query("select c from Car c where c.user.id = :pUserId and c.id = :pCarId" )
    Optional<Car> findByIdAndByUserId( @Param("pCarId") UUID pCarId, @Param("pUserId") UUID pUserId);

	Optional<Car> findByLicensePlate(String licensePlate);

}
