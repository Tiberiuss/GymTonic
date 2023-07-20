package com.gym.GymTonic.controller;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.payload.BaseResponse;
import com.gym.GymTonic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ExerciseDTO>>> findAll() {
        List<ExerciseDTO> exerciseList = service.findAll();
        BaseResponse.BaseResponseBuilder<List<ExerciseDTO>> builder = BaseResponse.builder();
        if (!exerciseList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(exerciseList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("1").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<BaseResponse<Page<ExerciseDTO>>> findAllWithPagination(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset, @RequestParam(value = "page", required = false, defaultValue = "10") int pageSize) {
        Page<ExerciseDTO> exerciseList = service.findAllWithPagination(offset, pageSize);
        BaseResponse.BaseResponseBuilder<Page<ExerciseDTO>> builder = BaseResponse.builder();
        if (!exerciseList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(exerciseList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("1").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponse<ExerciseDTO>> findExerciseById(@PathVariable String id) {
        BaseResponse.BaseResponseBuilder<ExerciseDTO> builder = BaseResponse.builder();
        try {
            ExerciseDTO exerciseDTO = service.findById(id);
            return new ResponseEntity<>(builder.status("1").data(exerciseDTO).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(builder.status("1").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "nom")
    public ResponseEntity<BaseResponse<List<ExerciseDTO>>> findExerciseByNameOrMuscleOrMaterial(@RequestParam String nom) {
        String muscle = nom;
        String material = nom;
        List<ExerciseDTO> exerciseDTOList = service.findByNameOrMuscleOrMaterial(nom, muscle, material);
        BaseResponse.BaseResponseBuilder<List<ExerciseDTO>> builder = BaseResponse.builder();
        if (!exerciseDTOList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(exerciseDTOList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity<BaseResponse<List<ExerciseDTO>>> findExerciseByName(@RequestParam("name") String name) {
        List<ExerciseDTO> exerciseDTOList = service.findByName(name);
        BaseResponse.BaseResponseBuilder<List<ExerciseDTO>> builder = BaseResponse.builder();
        if (!exerciseDTOList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(exerciseDTOList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    //@GetMapping("/name={name}/pages/{offset}/{pageSize}")
    @GetMapping("/name={name}/pages")
    public ResponseEntity<BaseResponse<Page<ExerciseDTO>>> findExerciseByNameWithPagination(@PathVariable String name, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset, @RequestParam(value = "page", required = false, defaultValue = "10") int pageSize) {
        Page<ExerciseDTO> exerciseDTOList = service.findByNameWithPagination(name, offset, pageSize);
        BaseResponse.BaseResponseBuilder<Page<ExerciseDTO>> builder = BaseResponse.builder();

        if (!exerciseDTOList.isEmpty()) {
            return new ResponseEntity<>(builder.status("1").data(exerciseDTOList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(builder.status("0").message("Not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ExerciseDTO exerciseDTO) {
        service.create(exerciseDTO);
        return new ResponseEntity<>(BaseResponse.builder().status("1").message("Saved").build(), HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id, @RequestBody ExerciseDTO exerciseDTO) {
        try {
            service.update(id, exerciseDTO);
            return new ResponseEntity<>(BaseResponse.builder().status("1").data(service.findById(id)).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String id) {
        try {
            ExerciseDTO exerciseDTO = service.findById(id);
            service.delete(id);
            return new ResponseEntity<>(BaseResponse.builder().status("1").message("Deleted").build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(BaseResponse.builder().status("0").message("Data is not found").build(), HttpStatus.NOT_FOUND);
        }
    }
}
