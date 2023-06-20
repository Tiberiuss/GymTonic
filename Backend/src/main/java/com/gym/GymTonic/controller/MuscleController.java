package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.service.MuscleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/muscle")
public class MuscleController {
    private final MuscleService service;

    @Autowired
    public MuscleController(MuscleService service) {
        this.service = service;
    }
    @GetMapping
    public List<Muscle> findAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody Muscle muscle){
        service.create(muscle);
    }
}
