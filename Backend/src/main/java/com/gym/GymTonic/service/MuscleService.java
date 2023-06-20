package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.repository.MuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MuscleService {
    private final MuscleRepository repository;

    @Autowired
    public MuscleService(MuscleRepository repository) {
        this.repository = repository;
    }

    public List<Muscle> findAll() {
        return repository.findAll();
    }

    public void create(Muscle muscle) {
        repository.create(muscle);
    }
}
