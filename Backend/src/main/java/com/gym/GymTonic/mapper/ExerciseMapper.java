package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.model.elastic.ExerciseElastic;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ExerciseMapper {
    ExerciseMongo toEntityMongo(ExerciseDTO exerciseDTO);
    ExerciseElastic toEntityElastic(ExerciseDTO exerciseDTO);
    ExerciseDTO toDTO(ExerciseElastic exerciseElastic);
    ExerciseDTO toDTO(ExerciseMongo exerciseMongo);
}
