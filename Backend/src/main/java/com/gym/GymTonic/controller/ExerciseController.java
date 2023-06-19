package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.Exercise;
import com.gym.GymTonic.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    private final ExerciseService service;

    @Autowired
    public ExerciseController(ExerciseService service) {
        this.service = service;
    }
    @GetMapping
    public List<Exercise> findAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody Exercise exercise){
        service.create(exercise);
    }
}
