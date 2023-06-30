package com.gym.GymTonic.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.GymTonic.model.elastic.Exercise;
import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.service.ExerciseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JSONLoader {

    private final ExerciseService exerciseService;

    private final ElasticsearchOperations elasticsearchOperations;

    public JSONLoader(ExerciseService exerciseService, ElasticsearchOperations elasticsearchOperations) {
        this.exerciseService = exerciseService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Value("classpath:workout-data.json")
    private Resource resourceFile;

    @PostConstruct
    private void initDB() {
        elasticsearchOperations.indexOps(IndexCoordinates.of("exercise")).delete();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode exercise = objectMapper.readTree(resourceFile.getInputStream());

            for (JsonNode el : exercise) {
                exerciseService.create(new Exercise(el.get("exercise_name").textValue(),
                        Material.valueOf(el.get("Category").textValue().toUpperCase()),
                        StreamSupport.stream(el.get("target").spliterator(), false).map(i -> StreamSupport.stream(i.spliterator(), false).map(b ->
                                Muscle.fromString(b.textValue().toUpperCase())
                        ).collect(Collectors.toSet())).flatMap(Set::stream).collect(Collectors.toSet()),
                        StreamSupport.stream(el.get("videoURL").spliterator(),false).map(JsonNode::textValue).toList()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
