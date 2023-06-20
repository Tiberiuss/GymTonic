package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Exercise;
import com.gym.GymTonic.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public void create(Exercise exercise) {
        repository.save(exercise);
    }
}
