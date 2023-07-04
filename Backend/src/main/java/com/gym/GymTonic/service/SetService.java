package com.gym.GymTonic.service;

import com.gym.GymTonic.model.mongo.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gym.GymTonic.model.mongo.Set;
import com.gym.GymTonic.repository.mongo.SetRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SetService {
    private final SetRepository repository;

    public List<Set> findAll() {
        return repository.findAll();
    }

    public void create(Set set) {
        repository.save(set);
    }

    public Set findById(String id) {
        return repository.findById(id).get();
    }

    public void update(String id, Set set) {
        set.setId(id);
        repository.save(set);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
