package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.repository.MuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@Service
public class MuscleService {
    private final MuscleRepository repository;

    @Autowired
    public MuscleService(MuscleRepository repository) {
        this.repository = repository;
    }

    public List<Muscle> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Muscle findMuscleById(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    public int create(Muscle muscle) {
        try{
            repository.save(muscle);
            return 200;
        }catch(Exception e){
            return 500;
        }
    }

    public int delete(Integer id){
        /*int i = 0;
        boolean found = false;
        List<Muscle> muscles = repository.findAll();
        while(i < muscles.size() && !found){
            if(muscles.get(i).getId().equals(id)){
                found = true;
                //muscles.set(i, muscle);
                repository.deleteById(id);
            }
            else{
                i++;
            }
        }
        if(found){
            return 200;
        }
        else {
            return 500;
        }*/
        try{
            Muscle m = repository.findById(id).get();
            repository.delete(m);
            return 200;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 500;
        }


    }

    public int update(Integer id, Muscle muscle){
        /*int i = 0;
        boolean found = false;
        List<Muscle> muscles = repository.findAll();
        while(i < muscles.size() && !found){
            if(muscles.get(i).getId().equals(id)){
                found = true;
                muscles.set(i, muscle);
                repository.save(muscle);
            }
            else{
                i++;
            }
        }
        if(found){
            return 200;
        }
        else{
            return 500;
        }*/
        try {
            Muscle m = repository.findById(id).get();
            m.setId(id);
            m.setMuscle_name(muscle.getMuscle_name());
            m.setMuscle_group(muscle.getMuscle_group());
            repository.save(m);
            return 200;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
