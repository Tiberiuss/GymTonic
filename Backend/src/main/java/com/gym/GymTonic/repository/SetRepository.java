package com.gym.GymTonic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.gym.GymTonic.model.Set;

public interface SetRepository extends ElasticsearchRepository<Set, Integer> {
}
