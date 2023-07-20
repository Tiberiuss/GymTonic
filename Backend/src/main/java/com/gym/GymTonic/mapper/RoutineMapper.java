package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.RoutineDTO;
import com.gym.GymTonic.model.mongo.Routine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
        //uses = UserMapper.class
)
public interface RoutineMapper {
    Routine toEntity(RoutineDTO routineDTO);

    RoutineDTO toDTO(Routine routine);

}
