package com.gym.GymTonic.repository.elastic;

import com.gym.GymTonic.model.elastic.ExerciseElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ExerciseElasticRepository extends ElasticsearchRepository<ExerciseElastic, String> {
    List<ExerciseElastic> findByName(String name);

    List<ExerciseElastic> findAll();
    //ResponseEntity<List<Exercise>> findAll();
}
