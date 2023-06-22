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

    public MuscleGroup findMuscleGroupById(Integer id){
        return repository.findById(id).get();
    }

    public void create(MuscleGroup muscle_group) {
        repository.save(muscle_group);
    }

    public int delete(Integer id){
        try{
            repository.deleteById(id);
            return 200;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 500;
        }
    }

    public int update(Integer id, MuscleGroup muscle_group){
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
