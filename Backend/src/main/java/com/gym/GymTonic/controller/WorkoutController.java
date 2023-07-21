package com.gym.GymTonic.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gym.GymTonic.dto.Views;
import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.payload.BaseResponse;
import com.gym.GymTonic.payload.ChartResponse;
import com.gym.GymTonic.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workout")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {
    private final WorkoutService service;

    @GetMapping("/exercise={id}")
    public ResponseEntity<BaseResponse<List<ChartResponse>>> findWorkoutsByExercise(@PathVariable String id) {
        BaseResponse.BaseResponseBuilder<List<ChartResponse>> builder = BaseResponse.builder();
        try {
            List<ChartResponse> workoutDTOS = service.findByExercise(id);
            return new ResponseEntity<>(builder.status("1").data(workoutDTOS).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(builder.status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date_user")
    public ResponseEntity<BaseResponse<List<WorkoutDTO>>> findWorkoutsByExercise(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        BaseResponse.BaseResponseBuilder<List<WorkoutDTO>> builder = BaseResponse.builder();
        try {
            List<WorkoutDTO> workoutDTOS = service.findByDateAndUserId(date);
            return new ResponseEntity<>(builder.status("1").data(workoutDTOS).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(builder.status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<WorkoutDTO>>> findAll() {
        List<WorkoutDTO> workoutList = service.findAll();
        BaseResponse.BaseResponseBuilder<List<WorkoutDTO>> builder = BaseResponse.builder();
        if (!workoutList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(workoutList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponse<WorkoutDTO>> findRoutineById(@PathVariable String id) {
        BaseResponse.BaseResponseBuilder<WorkoutDTO> builder = BaseResponse.builder();
        try {
            WorkoutDTO workout = service.findById(id);
            return new ResponseEntity<>(builder.status("1").data(workout).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(builder.status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @JsonView(Views.CreateWorkout.class) WorkoutDTO workout) {
        service.create(workout);
        return new ResponseEntity<>(BaseResponse.builder().status("1").message("Saved").build(), HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id, @RequestBody @JsonView(Views.CreateWorkout.class) WorkoutDTO workout) {
        try {
            service.update(id, workout);
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(service.findById(id)).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String id) {
        try {
            service.findById(id);
            service.delete(id);
            return new ResponseEntity<>(BaseResponse.builder().status("1").message("Deleted").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }
}
