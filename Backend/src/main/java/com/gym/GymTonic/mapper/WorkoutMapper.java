package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.RoutineDTO;
import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.model.mongo.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
        //uses = UserMapper.class
)
public interface WorkoutMapper {
    Workout toEntity(WorkoutDTO workoutDTO);
    WorkoutDTO toDTO(Workout workout);

}
