package com.gym.GymTonic.controller;

import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.model.mongo.Workout;
import com.gym.GymTonic.payload.BaseResponse;
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
    public ResponseEntity<BaseResponse> findAll() {
        List<WorkoutDTO> workoutList = service.findAll();
        if (!workoutList.isEmpty()) {
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(workoutList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponse> findRoutineById(@PathVariable String id) {
        try {
            WorkoutDTO workout = service.findById(id);
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(workout).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody WorkoutDTO workout){
        service.create(workout);
        return new ResponseEntity<>(BaseResponse.builder().status("1").message("Saved").build(), HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id, @RequestBody WorkoutDTO workout){
        try{
            service.update(id, workout);
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(service.findById(id)).build(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String id){
        try {
            service.findById(id);
            service.delete(id);
            return new ResponseEntity<>(BaseResponse.builder().status("1").message("Deleted").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }
}
