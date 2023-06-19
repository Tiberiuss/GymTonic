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
}
