package com.gym.GymTonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.GymTonic.model.Set;
import com.gym.GymTonic.repository.SetRepository;
import java.util.List;

@Service
public class SetService {
    private final SetRepository repository;

    @Autowired
    public SetService(SetRepository repository) {
        this.repository = repository;
    }

    public List<Set> findAll() {
        return repository.findAll();
    }

    public void create(Set set) {
        repository.save(set);
    }

    public Set findById(Integer id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int update(Set set) {
        try{
            repository.save(set);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }

    public int delete(Set set) {
        try{
            repository.delete(set);
            return 200;
        }catch (Exception e){
            System.out.println(e);
            return 500;
        }
    }
}
