package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.MuscleGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public interface MuscleGroupRepository extends ElasticsearchRepository<MuscleGroup, String> {

}
