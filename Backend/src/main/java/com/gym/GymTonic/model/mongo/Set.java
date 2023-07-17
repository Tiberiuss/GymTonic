package com.gym.GymTonic.model.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Set {
    private Integer number;
    private Integer reps;
    private Float weight;

    @DBRef
    private ExerciseMongo exerciseMongo;
}
