package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.elastic.Exercise;
import com.gym.GymTonic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping()
    public List<Exercise> findAll() {
        return service.findAll();
    }

    @GetMapping("/id={id}")
    public Exercise findExerciseById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/name={name}")
    public List<Exercise> findExerciseByName(@PathVariable String name){
        return service.findByName(name);
    }

    @PostMapping
    public int create(@RequestBody Exercise exercise){
        return service.create(exercise);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable String id, @RequestBody Exercise exercise){
        return service.update(id, exercise);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable String id){
        return service.delete(id);
    }
}
