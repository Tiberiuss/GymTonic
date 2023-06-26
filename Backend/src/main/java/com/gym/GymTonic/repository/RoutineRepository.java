package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Routine;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface RoutineRepository extends ElasticsearchRepository<Routine,Integer> {
}