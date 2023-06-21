package com.gym.GymTonic.service;

import com.gym.GymTonic.model.Routine;
import com.gym.GymTonic.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoutineService {
    private final RoutineRepository repository;

    @Autowired
    public RoutineService(RoutineRepository repository){
        this.repository = repository;
    }

    public List<Routine> findAll() {
        return repository.findAll();
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

    public int update(Routine routine) {
        try{
            repository.save(routine);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(Routine routine) {
        try{
            repository.delete(routine);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
