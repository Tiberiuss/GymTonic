package com.gym.GymTonic.mapper;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.model.elastic.ExerciseElastic;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T11:14:21+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Eclipse Adoptium)"
)
@Component
public class ExerciseMapperImpl implements ExerciseMapper {

    @Override
    public ExerciseMongo toEntityMongo(ExerciseDTO exerciseDTO) {
        if ( exerciseDTO == null ) {
            return null;
        }

        ExerciseMongo exerciseMongo = new ExerciseMongo();

        exerciseMongo.setId( exerciseDTO.getId() );
        exerciseMongo.setName( exerciseDTO.getName() );
        exerciseMongo.setMaterial( exerciseDTO.getMaterial() );
        Collection<Muscle> collection = exerciseDTO.getMuscle();
        if ( collection != null ) {
            exerciseMongo.setMuscle( new ArrayList<Muscle>( collection ) );
        }

        return exerciseMongo;
    }

    @Override
    public ExerciseElastic toEntityElastic(ExerciseDTO exerciseDTO) {
        if ( exerciseDTO == null ) {
            return null;
        }

        ExerciseElastic exerciseElastic = new ExerciseElastic();

        exerciseElastic.setId( exerciseDTO.getId() );
        exerciseElastic.setName( exerciseDTO.getName() );
        exerciseElastic.setMaterial( exerciseDTO.getMaterial() );
        Collection<Muscle> collection = exerciseDTO.getMuscle();
        if ( collection != null ) {
            exerciseElastic.setMuscle( new ArrayList<Muscle>( collection ) );
        }
        Collection<String> collection1 = exerciseDTO.getVideo();
        if ( collection1 != null ) {
            exerciseElastic.setVideo( new ArrayList<String>( collection1 ) );
        }

        return exerciseElastic;
    }

    @Override
    public ExerciseDTO toDTO(ExerciseElastic exerciseElastic) {
        if ( exerciseElastic == null ) {
            return null;
        }

        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setId( exerciseElastic.getId() );
        exerciseDTO.setName( exerciseElastic.getName() );
        exerciseDTO.setMaterial( exerciseElastic.getMaterial() );
        Collection<Muscle> collection = exerciseElastic.getMuscle();
        if ( collection != null ) {
            exerciseDTO.setMuscle( new ArrayList<Muscle>( collection ) );
        }
        Collection<String> collection1 = exerciseElastic.getVideo();
        if ( collection1 != null ) {
            exerciseDTO.setVideo( new ArrayList<String>( collection1 ) );
        }

        return exerciseDTO;
    }

    @Override
    public ExerciseDTO toDTO(ExerciseMongo exerciseMongo) {
        if ( exerciseMongo == null ) {
            return null;
        }

        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setId( exerciseMongo.getId() );
        exerciseDTO.setName( exerciseMongo.getName() );
        exerciseDTO.setMaterial( exerciseMongo.getMaterial() );
        Collection<Muscle> collection = exerciseMongo.getMuscle();
        if ( collection != null ) {
            exerciseDTO.setMuscle( new ArrayList<Muscle>( collection ) );
        }

        return exerciseDTO;
    }
}
