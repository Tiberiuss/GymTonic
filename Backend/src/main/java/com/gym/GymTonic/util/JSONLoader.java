package com.gym.GymTonic.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.GymTonic.dto.ExerciseDTO;
import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JSONLoader {

    private final ExerciseService exerciseService;
    private final ElasticsearchOperations elasticsearchOperations;
    private final MongoTemplate mongoTemplate;

    @Value("classpath:workout-data.json")
    private Resource resourceFile;

    @PostConstruct
    private void initDB() {
        if (mongoTemplate.estimatedCount("exerciseMongo") > 0) {
            return;
        }
        elasticsearchOperations.indexOps(IndexCoordinates.of("exercise")).delete();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode exercise = objectMapper.readTree(resourceFile.getInputStream());
            for (JsonNode el : exercise) {
                String id = null;
                String name = el.get("exercise_name").textValue();
                Material material = Material.valueOf(el.get("Category").textValue().toUpperCase());

                Set<Muscle> muscle = StreamSupport.stream(el.get("target").spliterator(), false)
                        .flatMap(i -> StreamSupport.stream(i.spliterator(), false))
                        .map(b -> Muscle.fromString(b.textValue().toUpperCase()))
                        .collect(Collectors.toSet());

                List<String> videos = StreamSupport.stream(el.get("videoURL").spliterator(), false)
                        .map(JsonNode::textValue)
                        .toList();
                List<String> steps = StreamSupport.stream(el.get("steps").spliterator(),false)
                        .map(JsonNode::textValue)
                        .toList();
                ExerciseDTO exerciseDTO = new ExerciseDTO(id, name, material, muscle, videos,steps);
                exerciseService.create(exerciseDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
