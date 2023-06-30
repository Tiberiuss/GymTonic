package com.gym.GymTonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.GymTonic.model.mongo.Set;
import com.gym.GymTonic.repository.mongo.SetRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SetService {
    private final SetRepository repository;

    @Autowired
    public SetService(SetRepository repository) {
        this.repository = repository;
    }

    public List<Set> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public void create(Set set) {
        repository.save(set);
    }

    public Set findById(String id) {
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int update(String id, Set set) {
        try{
            if (repository.findById(id).isEmpty()) {
                return 500;
            }
            set.setId(id);
            repository.save(set);
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
