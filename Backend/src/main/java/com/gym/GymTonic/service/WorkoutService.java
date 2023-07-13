package com.gym.GymTonic.service;

import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.mapper.WorkoutMapper;
import com.gym.GymTonic.model.mongo.Set;
import com.gym.GymTonic.model.mongo.Workout;
import com.gym.GymTonic.repository.mongo.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public List<WorkoutDTO> findAll(){
        return repository.findAll().stream().map(mapper::toDTO).toList();

    }

    public Workout findById(String id) {
        return repository.findById(id).get();
    }

    public void create(Workout workout){
        repository.save(workout);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public void update(String id, Workout workout){
        workout.setId(id);
        repository.save(workout);
    }
}
