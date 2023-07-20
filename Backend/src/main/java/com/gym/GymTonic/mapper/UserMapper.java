package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.UserDTO;
import com.gym.GymTonic.model.mongo.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UserMapper {
    UserModel toEntity(UserDTO exerciseDTO);

    UserDTO toDTO(UserModel user);
}
