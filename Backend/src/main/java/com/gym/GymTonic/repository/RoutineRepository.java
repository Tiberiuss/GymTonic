package com.gym.GymTonic.repository;

import com.gym.GymTonic.model.Routine;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoutineRepository {
    private final List<Routine> routineList = new ArrayList<>();

    public List<Routine> findAll() {
        return routineList;
    }

    public void create(Routine routine) {
        routineList.add(routine);
    }
}