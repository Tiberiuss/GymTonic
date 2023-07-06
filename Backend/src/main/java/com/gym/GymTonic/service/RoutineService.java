package com.gym.GymTonic.service;

import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.repository.mongo.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository repository;

    public List<Routine> findAll() {
        return repository.findAll();
    }

    public void create(Routine routine) {
        repository.save(routine);
    }

    public Routine findById(String id) {
        return repository.findById(id).get();
    }

    public void update(String id, Routine routine) {
        routine.setId(id);
        repository.save(routine);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
