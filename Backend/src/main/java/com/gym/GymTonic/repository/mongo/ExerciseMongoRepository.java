package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExerciseMongoRepository extends MongoRepository<Exercise, String> {

}
