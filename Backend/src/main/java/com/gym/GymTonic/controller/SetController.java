package com.gym.GymTonic.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gym.GymTonic.model.mongo.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin(origins = "*")
public class SetController {
    
    private final SetService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Set> setList = service.findAll();
        if (!setList.isEmpty()) {
            map.put("status", 1);
            map.put("data", setList);
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
            Set set = service.findById(id);
            map.put("status", 1);
            map.put("data", set);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Set set){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        service.create(set);
        map.put("status", 1);
        map.put("message", "Saved");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Set set){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            service.update(id, set);
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
            Set set = service.findById(id);
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
