package com.gym.GymTonic.service;

import com.gym.GymTonic.model.elastic.Exercise;
import com.gym.GymTonic.repository.elastic.ExerciseElasticRepository;
import com.gym.GymTonic.repository.mongo.ExerciseMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseElasticRepository repository;
    private final ExerciseMongoRepository repositoryMongo;

    public List<Exercise> findAll() {
        return repository.findAll();
    }


    public void create(Exercise exercise) {
        repository.save(exercise);
    }

    public Exercise findById(String id) {
        return repository.findById(id).get();
    }

    public List<Exercise> findByName(String name){
        return repository.findByName(name);
    }


    public void update(String id, Exercise exercise) {
        exercise.setId(id);
        repository.save(exercise);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
