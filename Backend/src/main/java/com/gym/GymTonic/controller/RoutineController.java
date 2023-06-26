package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.Routine;
import com.gym.GymTonic.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routine")
public class RoutineController {

    private final RoutineService service;

    @Autowired
    public RoutineController(RoutineService service) {
        this.service = service;
    }

    @GetMapping
    public List<Routine> findAll() {
        return service.findAll();
    }

    @GetMapping("/id={id}")
    public Routine findExerciseById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Routine routine) {
        service.create(routine);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable String id, @RequestBody Routine routine){
        return service.update(id, routine);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable String id){
        return service.delete(id);
    }
}