package com.gym.GymTonic.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.GymTonic.model.mongo.Set;
import com.gym.GymTonic.service.SetService;

@RestController
@RequestMapping("/api/v1/set")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SetController {
    
    private final SetService service;

    @GetMapping
    public List<Set> findAll() {
        return service.findAll();
    }

    @GetMapping("/id={id}")
    public Set findExerciseById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Set set) {
        service.create(set);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable String id, @RequestBody Set set){
        return service.update(id, set);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable String id){
        return service.delete(id);
    }
}
