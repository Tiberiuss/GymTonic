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

    public int create(Exercise exercise) {
        try{
            repository.save(exercise);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public Exercise findById(Integer id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int update(Exercise exercise) {
        try{
            repository.save(exercise);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(Exercise exercise) {
        try{
            repository.delete(exercise);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
