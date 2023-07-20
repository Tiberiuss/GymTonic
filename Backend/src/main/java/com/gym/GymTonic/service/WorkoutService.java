package com.gym.GymTonic.service;

import com.gym.GymTonic.payload.ChartResponse;
import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.mapper.WorkoutMapper;
import com.gym.GymTonic.model.mongo.Workout;
import com.gym.GymTonic.repository.mongo.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public List<WorkoutDTO> findAll(){
        return repository.findAllByUserId(AuthService.getAuthentication().getId()).stream().map(mapper::toDTO).toList();

    }

    public WorkoutDTO findById(String id) {
        return mapper.toDTO(repository.findByIdAndUserId(id,AuthService.getAuthentication().getId()).get());
    }

    public void create(WorkoutDTO workout){
        Workout entity = mapper.toEntity(workout);
        entity.setUser(AuthService.getAuthentication());
        repository.save(entity);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public void update(String id, WorkoutDTO workout){
        workout.setId(id);
        repository.save(mapper.toEntity(workout));
    }

    public List<ChartResponse> findByExercise(String id) {
        String user_id = AuthService.getAuthentication().getId();
        List<Workout> workouts = repository.findByUserId(user_id);

        workouts.forEach(i -> i.getSet().removeIf(set -> !set.getExerciseMongo().getId().equals(id)));


        List<ChartResponse> chartResponses = new ArrayList<>();
        for (Workout w: workouts){
            LocalDate localDate = w.getDate();
            w.getSet().stream()
                    .filter(set -> set.getExerciseMongo().getId().equals(id))
                    .map(set -> new ChartResponse(localDate, set.getWeight()))
                    .collect(Collectors.groupingBy(ChartResponse::getDate, Collectors.maxBy(Comparator.comparing(ChartResponse::getWeight))))
                    .values()
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toCollection(() -> chartResponses));
        }

        return chartResponses;
    }
}
