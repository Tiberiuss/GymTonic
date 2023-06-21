package com.gym.GymTonic.service;

import com.gym.GymTonic.model.MuscleGroup;
import com.gym.GymTonic.repository.MuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MuscleGroupService {
    private final MuscleGroupRepository repository;

    @Autowired
    public MuscleGroupService(MuscleGroupRepository repository) {
        this.repository = repository;
    }

    public List<MuscleGroup> findAll() {
        return repository.findAll();
    }

    public void create(MuscleGroup muscle_group) {
        repository.save(muscle_group);
    }
}
