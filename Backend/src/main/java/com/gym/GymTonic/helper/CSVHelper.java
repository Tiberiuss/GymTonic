package com.gym.GymTonic.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;

public class CSVHelper {
    public static void initDB(){
        String filename = "classpath:workout-data.csv";

        try (CSVParser csvParser = new CSVParser(new FileReader(ResourceUtils.getFile(filename)), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(csvRecord.get("exercise_name"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
