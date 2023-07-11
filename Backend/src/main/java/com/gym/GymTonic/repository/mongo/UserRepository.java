package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel,String> {
    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);
}
