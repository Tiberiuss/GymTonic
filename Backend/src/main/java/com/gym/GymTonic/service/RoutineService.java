package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Routine;
import com.gym.GymTonic.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoutineService {
    private final RoutineRepository repository;

    @Autowired
    public RoutineService(RoutineRepository repository){
        this.repository = repository;
    }

    public List<Routine> findAll() {
        return repository.findAll();
    }

    public void create(Routine routine) {
        repository.create(routine);
    }
}
