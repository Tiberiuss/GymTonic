package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Integer> {

}
