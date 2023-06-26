package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.MuscleGroup;
import com.gym.GymTonic.service.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
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

    @GetMapping("/id={id}")
    public MuscleGroup findMuscleGroupById(@PathVariable Integer id){
        return service.findMuscleGroupById(id);
    }

    @PostMapping
    public int create(@RequestBody MuscleGroup muscle_group){
        return service.create(muscle_group);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable Integer id, @RequestBody MuscleGroup muscle_group){
        return service.update(id, muscle_group);
    }
}