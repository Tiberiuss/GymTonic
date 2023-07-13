package com.gym.GymTonic.service;

import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.mapper.ExerciseMapper;
import com.gym.GymTonic.model.elastic.ExerciseElastic;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.repository.elastic.ExerciseElasticRepository;
import com.gym.GymTonic.repository.mongo.ExerciseMongoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseElasticRepository repositoryElastic;
    private final ExerciseMongoRepository repositoryMongo;
    private final ModelMapper modelMapper;
    private final ExerciseMapper mapper;

    public List<ExerciseDTO> findAll() {
        return repositoryElastic.findAll().stream().map(mapper::toDTO).toList();
    }

    public Page<ExerciseDTO> findAllWithPagination(int offset, int pageSize) {
        return (Page<ExerciseDTO>) repositoryElastic.findAll(PageRequest.of(offset, pageSize)).map(this::convertEntityToDTO);
    }


    public void create(ExerciseDTO exerciseDTO) {
        repositoryElastic.save(convertDTOToElasticEntity(exerciseDTO));
        repositoryMongo.save(convertDTOToMongoEntity(exerciseDTO));
    }

    public ExerciseDTO findById(String id) {
        return convertEntityToDTO(repositoryElastic.findById(id).get());
    }

    public List<ExerciseDTO> findByName(String name){
        return repositoryElastic.findByNameContaining(name).stream().map(this::convertEntityToDTO).toList();
    }

    public List<ExerciseDTO> findByNameOrMuscleOrMaterial(String name, String muscle, String material){
        return repositoryElastic.findByNameOrMuscleOrMaterialContaining(name, muscle, material).stream().map(this::convertEntityToDTO).toList();
    }

    public Page<ExerciseDTO> findByNameWithPagination(String name, int offset, int pageSize){
        return (Page<ExerciseDTO>) repositoryElastic.findAllByNameContaining(name, PageRequest.of(offset, pageSize)).map(this::convertEntityToDTO);
    }


    public void update(String id, ExerciseDTO exerciseDTO) {
        exerciseDTO.setId(id);
        repositoryElastic.save(convertDTOToElasticEntity(exerciseDTO));
        repositoryMongo.save(convertDTOToMongoEntity(exerciseDTO));
    }

    public void delete(String id) {
        repositoryElastic.deleteById(id);
    }

    private ExerciseDTO convertEntityToDTO(ExerciseElastic exerciseElastic){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO = modelMapper.map(exerciseElastic, ExerciseDTO.class);
        return exerciseDTO;
    }

    private ExerciseElastic convertDTOToElasticEntity(ExerciseDTO exerciseDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ExerciseElastic exerciseElastic = new ExerciseElastic();
        exerciseElastic = modelMapper.map(exerciseDTO, ExerciseElastic.class);
        return exerciseElastic;
    }

    private ExerciseMongo convertDTOToMongoEntity(ExerciseDTO exerciseDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ExerciseMongo exerciseMongo = new ExerciseMongo();
        exerciseMongo = modelMapper.map(exerciseDTO, ExerciseMongo.class);
        return exerciseMongo;
    }
}

