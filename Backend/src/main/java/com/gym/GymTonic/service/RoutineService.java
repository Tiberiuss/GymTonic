package com.gym.GymTonic.service;

import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.repository.mongo.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository repository;

    public List<Routine> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public void create(Routine routine) {
        repository.save(routine);
    }

    public Routine findById(String id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int update(String id, Routine routine) {
        try{
            if (repository.findById(id).isEmpty()) {
                return 500;
            }
            routine.setId(id);
            repository.save(routine);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(String id) {
        try{
            repository.deleteById(id);;
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
