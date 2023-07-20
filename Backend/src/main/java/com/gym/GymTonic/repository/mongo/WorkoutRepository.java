package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
    List<Workout> findByUserId(String user_id);
    List<Workout> findAllByUserId(String id);

    Optional<Workout> findByIdAndUserId(String id, String user_id);
}
