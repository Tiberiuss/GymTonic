package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{
    List<Exercise> findByName(String name);
}
