package com.erick.challenge.api.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.erick.challenge.api.domain.User;
import com.erick.challenge.api.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO toUserDTO(User user);

	User toUserEntity(UserDTO userDTO);

}
