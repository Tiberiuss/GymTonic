package com.gym.GymTonic.service;

import com.gym.GymTonic.dto.WorkoutDTO;
import com.gym.GymTonic.mapper.WorkoutMapper;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.model.mongo.Workout;
import com.gym.GymTonic.payload.ExerciseChartResponse;
import com.gym.GymTonic.payload.ExerciseProgressResponse;
import com.gym.GymTonic.repository.mongo.ExerciseMongoRepository;
import com.gym.GymTonic.repository.mongo.WorkoutRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final ExerciseMongoRepository exerciseMongoRepository;
    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public List<WorkoutDTO> findAll() {
        return repository.findAllByUserId(AuthService.getAuthentication().getId()).stream().map(mapper::toDTO).toList();

    }

    public WorkoutDTO findById(String id) {
        return mapper.toDTO(repository.findByIdAndUserId(id, AuthService.getAuthentication().getId()).get());
    }

    public List<WorkoutDTO> findByDateAndUserId(Date date) {
        return repository.findByDateAndUserId(date, AuthService.getAuthentication().getId()).stream().map(mapper::toDTO).toList();

    }

    public void create(WorkoutDTO workout) {
        Workout entity = mapper.toEntity(workout);
        entity.setUser(AuthService.getAuthentication());
        repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public void update(String id, WorkoutDTO workout) {
        Workout entity = mapper.toEntity(workout);
        entity.setUser(AuthService.getAuthentication());
        entity.setId(id);
        repository.save(entity);
    }

    public ExerciseChartResponse findByExercise(String id) {
        String user_id = AuthService.getAuthentication().getId();
        List<Workout> workouts = repository.findByUserIdOrderByDate(user_id);
        ExerciseMongo exercise = exerciseMongoRepository.findById(id).get();

        workouts.forEach(i -> i.getSet().removeIf(set -> !set.getExerciseMongo().getId().equals(id)));


        List<ExerciseProgressResponse> chartResponses = new ArrayList<>();
        for (Workout w : workouts) {
            LocalDate localDate = w.getDate();
            w.getSet().stream()
                    .filter(set -> set.getExerciseMongo().getId().equals(id))
                    .map(set -> new ExerciseProgressResponse(localDate, set.getWeight()))
                    .collect(Collectors.groupingBy(ExerciseProgressResponse::getDate, Collectors.maxBy(Comparator.comparing(ExerciseProgressResponse::getWeight))))
                    .values()
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toCollection(() -> chartResponses));
        }
        return new ExerciseChartResponse(exercise.getName(),chartResponses);
    }
}
