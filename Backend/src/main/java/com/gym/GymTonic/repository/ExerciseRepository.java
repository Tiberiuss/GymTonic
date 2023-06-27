package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Exercise;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ExerciseRepository extends ElasticsearchRepository<Exercise, String>{
    List<Exercise> findByName(String name);
}
