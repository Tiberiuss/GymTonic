package com.gym.GymTonic.controller;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.model.elastic.ExerciseElastic;
import com.gym.GymTonic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
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
        List<ExerciseDTO> exerciseList = service.findAll();
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
            ExerciseDTO exerciseDTO = service.findById(id);
            map.put("status", 1);
            map.put("data", exerciseDTO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> findExerciseByName(@RequestParam("name") String name){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<ExerciseDTO> exerciseDTOList = service.findByName(name);
        if(!exerciseDTOList.isEmpty()){
            map.put("status", 1);
            map.put("data", exerciseDTOList);
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
    public ResponseEntity create(@RequestBody ExerciseDTO exerciseDTO){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        service.create(exerciseDTO);
        map.put("status", 1);
        map.put("message", "Saved");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ExerciseDTO exerciseDTO){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            service.update(id, exerciseDTO);
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
            ExerciseDTO exerciseDTO = service.findById(id);
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
