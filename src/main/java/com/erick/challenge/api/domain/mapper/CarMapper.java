package com.erick.challenge.api.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.erick.challenge.api.domain.Car;
import com.erick.challenge.api.domain.dto.CarDTO;

@Mapper
public interface CarMapper {
	CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

	CarDTO toCarDTO(Car car);

	Car toCarEntity(CarDTO carDTO);

}
