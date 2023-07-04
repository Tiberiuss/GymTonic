package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Routine;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoutineRepository extends MongoRepository<Routine,String> {
}