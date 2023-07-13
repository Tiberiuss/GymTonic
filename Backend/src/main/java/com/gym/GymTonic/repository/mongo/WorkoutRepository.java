package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
}
