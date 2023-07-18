package com.gym.GymTonic.service;

import com.gym.GymTonic.dto.RoutineDTO;
import com.gym.GymTonic.mapper.RoutineMapper;
import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.repository.mongo.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository repository;
    private final RoutineMapper mapper;

    public List<RoutineDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<RoutineDTO> findByUserId(String id){
        return repository.findByUserId(id).stream().map(mapper::toDTO).toList();
    }

    public List<RoutineDTO> findByDate(Date date){
        return repository.findByDate(date).stream().map(mapper::toDTO).toList();
    }

    public List<RoutineDTO> findByUserIdAndDate(String id, Date date){
        return repository.findByUserIdAndDate(id, date).stream().map(mapper::toDTO).toList();
    }

    public void create(RoutineDTO routine) {
        repository.save(mapper.toEntity(routine));
    }

    public RoutineDTO findById(String id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public void update(String id, RoutineDTO routine) {
        routine.setId(id);
        repository.save(mapper.toEntity(routine));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
