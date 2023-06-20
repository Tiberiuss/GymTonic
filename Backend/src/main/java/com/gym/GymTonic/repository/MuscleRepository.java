package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Muscle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MuscleRepository {
    private final List<Muscle> muscleList = new ArrayList<>();

    public List<Muscle> findAll() {
        return muscleList;
    }

    public void create(Muscle muscle) {
        muscleList.add(muscle);
    }

    public void delete_by_id(Integer id){
        for(Muscle muscle : this.muscleList){
            if(muscle.getId() == id){
                muscleList.remove(muscle);
            }
        }
    }
}
