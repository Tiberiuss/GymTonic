package com.gym.GymTonic.service;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.mapper.ExerciseMapper;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.repository.elastic.ExerciseElasticRepository;
import com.gym.GymTonic.repository.mongo.ExerciseMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseElasticRepository repositoryElastic;
    private final ExerciseMongoRepository repositoryMongo;
    private final ExerciseMapper mapper;

    public List<ExerciseDTO> findAll() {
        return repositoryElastic.findAll().stream().map(mapper::toDTO).toList();
    }

    public Page<ExerciseDTO> findAllWithPagination(int offset, int pageSize) {
        return repositoryElastic.findAll(PageRequest.of(offset, pageSize)).map(mapper::toDTO);
    }


    public void create(ExerciseDTO exerciseDTO) {
        ExerciseMongo exerciseMongo = repositoryMongo.save(mapper.toEntityMongo(exerciseDTO));
        exerciseDTO.setId(exerciseMongo.getId());
        repositoryElastic.save(mapper.toEntityElastic(exerciseDTO));
    }

    public ExerciseDTO findById(String id) {
        return mapper.toDTO(repositoryElastic.findById(id).get());
    }

    public List<ExerciseDTO> findByName(String name) {
        return repositoryElastic.findByNameContaining(name).stream().map(mapper::toDTO).toList();
    }

    public List<ExerciseDTO> findByNameOrMuscleOrMaterial(String name, String muscle, String material) {
        return repositoryElastic.findByNameOrMuscleOrMaterialContaining(name, muscle, material).stream().map(mapper::toDTO).toList();
    }

    public Page<ExerciseDTO> findByNameWithPagination(String name, int offset, int pageSize) {
        return repositoryElastic.findAllByNameStartingWith(name, PageRequest.of(offset, pageSize)).map(mapper::toDTO);
    }


    public void update(String id, ExerciseDTO exerciseDTO) {
        exerciseDTO.setId(id);
        repositoryElastic.save(mapper.toEntityElastic(exerciseDTO));
        repositoryMongo.save(mapper.toEntityMongo(exerciseDTO));
    }

    public void delete(String id) {
        repositoryElastic.deleteById(id);
    }

}

