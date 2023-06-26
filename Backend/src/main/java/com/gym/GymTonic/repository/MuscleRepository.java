package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Muscle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface MuscleRepository extends ElasticsearchRepository<Muscle, Integer> {

}
