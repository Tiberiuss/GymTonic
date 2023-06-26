package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Exercise;
import com.gym.GymTonic.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExerciseService {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<Exercise> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
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

    public int update(Integer id, Exercise exercise) {
        try{
            if (repository.findById(id).isEmpty()) {
                return 500;
            }
            exercise.setId(id);
            repository.save(exercise);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(Integer id) {
        try{
            repository.deleteById(id);;
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
