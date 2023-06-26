package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Exercise;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExerciseRepository extends ElasticsearchRepository<Exercise, String>{
}
