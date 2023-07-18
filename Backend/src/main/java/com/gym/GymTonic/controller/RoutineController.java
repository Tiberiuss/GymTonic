package com.gym.GymTonic.controller;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.dto.RoutineDTO;
import com.gym.GymTonic.payload.BaseResponse;
import com.gym.GymTonic.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/routine")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<RoutineDTO>>> findAll() {
        List<RoutineDTO> routineList = routineService.findAll();
        BaseResponse.BaseResponseBuilder<List<RoutineDTO>> builder = BaseResponse.builder();
        if (!routineList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(routineList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponse<RoutineDTO>> findRoutineById(@PathVariable String id) {
        BaseResponse.BaseResponseBuilder<RoutineDTO> builder = BaseResponse.builder();
        try {
            RoutineDTO routine = routineService.findById(id);
            return new ResponseEntity<>(builder.status("1").data(routine).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(builder.status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user_routine={user_routine}")
    public ResponseEntity<BaseResponse<List<RoutineDTO>>> findRoutineByUserId(@PathVariable String user_routine){
        List<RoutineDTO> routineList = routineService.findByUserId(user_routine);
        BaseResponse.BaseResponseBuilder<List<RoutineDTO>> builder = BaseResponse.builder();
        if (!routineList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(routineList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date={date}")
    public ResponseEntity<BaseResponse<List<RoutineDTO>>> findRoutineByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<RoutineDTO> routineList = routineService.findByDate(date);
        BaseResponse.BaseResponseBuilder<List<RoutineDTO>> builder = BaseResponse.builder();
        if (!routineList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(routineList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user_id={user_id}/date={date}")
    public ResponseEntity<BaseResponse<List<RoutineDTO>>> findRoutineByDate(@PathVariable String user_id, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<RoutineDTO> routineList = routineService.findByUserIdAndDate(user_id, date);
        BaseResponse.BaseResponseBuilder<List<RoutineDTO>> builder = BaseResponse.builder();
        if (!routineList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(routineList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody RoutineDTO routine){
        routineService.create(routine);
        return new ResponseEntity<>(BaseResponse.builder().status("1").message("Saved").build(), HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id, @RequestBody RoutineDTO routine){
        try{
            routineService.update(id, routine);
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(routineService.findById(id)).build(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String id){
        try {
            routineService.findById(id);
            routineService.delete(id);
            return new ResponseEntity<>(BaseResponse.builder().status("1").message("Deleted").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }
}