package com.gym.GymTonic.controller;

import com.gym.GymTonic.model.elastic.Exercise;
import com.gym.GymTonic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exercise")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Exercise> exerciseList = service.findAll();
        if(!exerciseList.isEmpty()){
            map.put("status", 1);
            map.put("data", exerciseList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else{
            map.clear();
            map.put("status", 0);
            map.put("message", "Not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<?> findExerciseById(@PathVariable String id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Exercise exercise = service.findById(id);
            map.put("status", 1);
            map.put("data", exercise);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name={name}")
    public ResponseEntity<?> findExerciseByName(@PathVariable String name){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Exercise> exerciseList = service.findByName(name);
        if(!exerciseList.isEmpty()){
            map.put("status", 1);
            map.put("data", exerciseList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else{
            map.clear();
            map.put("status", 0);
            map.put("message", "Not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Exercise exercise){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        service.create(exercise);
        map.put("status", 1);
        map.put("message", "Saved");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Exercise exercise){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            service.update(id, exercise);
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
            Exercise exercise = service.findById(id);
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
