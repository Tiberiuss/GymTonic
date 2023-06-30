package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Set;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SetRepository extends MongoRepository<Set, String> {
}
