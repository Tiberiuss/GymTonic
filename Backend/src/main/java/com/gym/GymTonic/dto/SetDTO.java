package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetDTO {
    private Integer number;
    private Integer reps;
    private Float weight;
    private ExerciseDTO exerciseMongo;
}
