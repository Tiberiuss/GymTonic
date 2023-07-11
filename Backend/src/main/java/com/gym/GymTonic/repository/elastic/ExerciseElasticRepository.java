package com.gym.GymTonic.repository.elastic;

import com.gym.GymTonic.model.elastic.ExerciseElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseElasticRepository extends ElasticsearchRepository<ExerciseElastic, String>{
    List<ExerciseElastic> findByNameContaining(String name);
    List<ExerciseElastic> findByNameOrMuscleOrMaterialContaining(String name, String muscle, String material);

    //Page<ExerciseElastic> findAllWithPagination(Pageable pageable);
    List<ExerciseElastic> findAll();
    Page<ExerciseElastic> findAllByNameContaining(String name, Pageable pageable);
}
