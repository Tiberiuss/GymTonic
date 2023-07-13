package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class SetDTO {
    @NonNull
    private Integer number;
    @NonNull
    private Integer reps;
    @NonNull
    private Float weight;
    @NonNull
    private ExerciseDTO exerciseMongo;
}
