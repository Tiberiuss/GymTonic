package com.gym.GymTonic.service;

import com.gym.GymTonic.model.elastic.Exercise;
import com.gym.GymTonic.repository.elastic.ExerciseElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseElasticRepository repository;

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public List<Exercise> findByName(String name){
        return repository.findByName(name);
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

    public Exercise findById(String id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /*public Exercise findByName(String name){
        try {
            List<Exercise> exercises = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
            Exercise e = new Exercise();
            boolean found = false;
            int i = 0;
            while (i < exercises.size() && found == false) {
                if (exercises.get(i).getName().equals(name)) {
                    e = exercises.get(i);
                    found = true;
                } else {
                    i++;
                }
            }
            return e;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    public int update(String id, Exercise exercise) {
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

    public int delete(String id) {
        try{
            repository.deleteById(id);;
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
