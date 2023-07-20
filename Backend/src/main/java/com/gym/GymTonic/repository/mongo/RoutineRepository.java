package com.gym.GymTonic.repository.mongo;

import com.gym.GymTonic.model.mongo.Routine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface RoutineRepository extends MongoRepository<Routine, String> {
    List<Routine> findByUserId(String id);

    List<Routine> findByDate(Date date);

    List<Routine> findByUserIdAndDate(String id, Date date);
}