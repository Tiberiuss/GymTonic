package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.SetDTO;
import com.gym.GymTonic.model.mongo.Set;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface SetMapper {
    Set toEntity(SetDTO setDTO);

    SetDTO toDTO(Set set);

}
