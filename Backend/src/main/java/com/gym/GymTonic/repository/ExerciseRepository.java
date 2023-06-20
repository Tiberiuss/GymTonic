package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Exercise;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ExerciseRepository {
    private final List<Exercise> exerciseList = new ArrayList<>();

    public List<Exercise> findAll() {
        return exerciseList;
    }

    public void create(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public void delete_by_id(Integer id) {
        for (Exercise exercise : this.exerciseList){
            if (exercise.getId() == id){
                exerciseList.remove(exercise);
            }
        }
    }
}
