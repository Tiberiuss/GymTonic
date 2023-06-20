package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.MuscleGroup;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MuscleGroupRepository {
    private final List<MuscleGroup> muscle_groupList = new ArrayList<>();

    public List<MuscleGroup> findAll() {
        return muscle_groupList;
    }

    public void create(MuscleGroup muscle_group) {
        muscle_groupList.add(muscle_group);
    }

    public void delete_by_id(Integer id){
        for(MuscleGroup muscle_group : this.muscle_groupList){
            if(muscle_group.getId() == id){
                muscle_groupList.remove(muscle_group);
            }
        }
    }
}
