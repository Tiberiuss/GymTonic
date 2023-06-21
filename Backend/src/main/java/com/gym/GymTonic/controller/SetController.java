package com.gym.GymTonic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.GymTonic.model.Set;
import com.gym.GymTonic.service.SetService;

@RestController
@RequestMapping("/api/v1/set")
public class SetController {
    
    private final SetService service;

    @Autowired
    public SetController(SetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Set> findAll() {
        return service.findAll();
    }

    @GetMapping("/id={id}")
    public Set findExerciseById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Set set) {
        service.create(set);
    }

    @PutMapping
    public int update(@RequestBody Set set){
        return service.update(set);
    }

    @DeleteMapping
    public int delete(@RequestBody Set set){
        return service.delete(set);
    }
}
