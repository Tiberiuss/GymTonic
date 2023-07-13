package com.gym.GymTonic.model.mongo;


import com.gym.GymTonic.model.mongo.ExerciseMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Set {
    @NonNull
    private Integer number;
    @NonNull
    private Integer reps;
    @NonNull
    private Float weight;
    @NonNull
    @DBRef
    private ExerciseMongo exerciseMongo;

}
