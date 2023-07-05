package com.gym.GymTonic.repository.elastic;

import com.gym.GymTonic.model.elastic.Exercise;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ExerciseElasticRepository extends ElasticsearchRepository<Exercise, String> {
    List<Exercise> findByName(String name);

    List<Exercise> findAll();
    //ResponseEntity<List<Exercise>> findAll();
}
