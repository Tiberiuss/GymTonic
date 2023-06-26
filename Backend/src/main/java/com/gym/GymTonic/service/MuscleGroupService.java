package com.gym.GymTonic.service;

import com.gym.GymTonic.model.MuscleGroup;
import com.gym.GymTonic.repository.MuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MuscleGroupService {
    private final MuscleGroupRepository repository;

    @Autowired
    public MuscleGroupService(MuscleGroupRepository repository) {
        this.repository = repository;
    }

    public List<MuscleGroup> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public MuscleGroup findMuscleGroupById(String id){
        return repository.findById(id).get();
    }

    public int create(MuscleGroup muscle_group) {
        try{
            repository.save(muscle_group);
            return 200;
        }catch(Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(String id){
        try{
            repository.deleteById(id);
            return 200;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 500;
        }
    }

    public int update(String id, MuscleGroup muscle_group){
        try{
            MuscleGroup mg = repository.findById(id).get();
            mg.setCategory(muscle_group.getCategory());
            mg.setId(id);
            repository.save(mg);
            return 200;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
