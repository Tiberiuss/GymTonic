package com.gym.GymTonic.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.GymTonic.model.Exercise;
import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.repository.ExerciseRepository;
import com.gym.GymTonic.service.ExerciseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class CSVHelper {

    private ExerciseService exerciseService;

    public CSVHelper(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Value("classpath:workout-data.json")
    private Resource resourceFile;

    @PostConstruct
    private void initDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode exercise = objectMapper.readTree(resourceFile.getInputStream());
            Iterator<JsonNode> iterator = exercise.iterator();

            for (JsonNode el : exercise) {
                exerciseService.create(new Exercise(el.get("exercise_name").textValue(),
                        Material.valueOf(el.get("Category").textValue().toUpperCase()),
                        StreamSupport.stream(el.get("target").spliterator(), false).map(i -> StreamSupport.stream(i.spliterator(), false).map(b -> new Muscle(b.textValue(), null)).collect(Collectors.toSet())).flatMap(Set::stream).collect(Collectors.toSet())
                ));
            }
            /*
            iterator.forEachRemaining(el -> {

            });*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
