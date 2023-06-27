package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.service.MuscleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/muscle")
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/id={id}")
    public Muscle findMuscleById(@PathVariable Integer id){
        return service.findMuscleById(id);
    }

    @PostMapping
    public int create(@RequestBody Muscle muscle){
        return service.create(muscle);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable Integer id, @RequestBody Muscle muscle){
        return service.update(id, muscle);
    }
}
