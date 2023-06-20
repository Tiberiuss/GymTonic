package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.MuscleGroup;
import com.gym.GymTonic.service.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/muscle_group")
public class MuscleGroupController {
    private final MuscleGroupService service;

    @Autowired
    public MuscleGroupController(MuscleGroupService service) {
        this.service = service;
    }
    @GetMapping
    public List<MuscleGroup> findAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody MuscleGroup muscle_group){
        service.create(muscle_group);
    }
}