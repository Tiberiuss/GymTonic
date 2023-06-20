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
}
