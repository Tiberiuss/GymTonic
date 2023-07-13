package com.gym.GymTonic.controller;

import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.model.mongo.Workout;
import com.gym.GymTonic.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/workout")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {
    private final WorkoutService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<WorkoutDTO> workoutList = service.findAll();
        if (!workoutList.isEmpty()) {
            map.put("status", 1);
            map.put("data", workoutList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<?> findRoutineById(@PathVariable String id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Workout workout = service.findById(id);
            map.put("status", 1);
            map.put("data", workout);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Workout workout){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        service.create(workout);
        map.put("status", 1);
        map.put("message", "Saved");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Workout workout){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            service.update(id, workout);
            map.put("status", 1);
            map.put("data", service.findById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity delete(@PathVariable String id){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Workout workout = service.findById(id);
            service.delete(id);
            map.put("status", 1);
            map.put("message", "Deleted");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
