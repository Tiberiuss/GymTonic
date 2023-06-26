package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Routine;
import com.gym.GymTonic.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoutineService {
    private final RoutineRepository repository;

    @Autowired
    public RoutineService(RoutineRepository repository){
        this.repository = repository;
    }

    public List<Routine> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public void create(Routine routine) {
        repository.save(routine);
    }

    public Routine findById(Integer id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int update(Integer id, Routine routine) {
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

    public int delete(Integer id) {
        try{
            repository.deleteById(id);;
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
