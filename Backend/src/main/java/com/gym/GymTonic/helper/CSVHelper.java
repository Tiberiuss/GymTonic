package com.gym.GymTonic.helper;

import com.gym.GymTonic.model.Exercise;
import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.repository.ExerciseRepository;
import com.gym.GymTonic.service.ExerciseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CSVHelper {

    private ExerciseService exerciseService;

    @PostConstruct
    private void initDB(){
        String filename = "classpath:workout-data.csv";

        try (CSVParser csvParser = new CSVParser(new FileReader(ResourceUtils.getFile(filename)), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
               // System.out.println(new Exercise(csvRecord.get("exercise_name"), Material.valueOf(csvRecord.get("Category"))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
