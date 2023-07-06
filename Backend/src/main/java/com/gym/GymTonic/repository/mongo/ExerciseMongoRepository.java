package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExerciseMongoRepository extends MongoRepository<ExerciseMongo, String> {

}
