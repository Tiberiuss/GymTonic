package com.gym.GymTonic.model.mongo;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


import java.util.Set;

@Document
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Routine {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Integer user_id;
    @NonNull
    private LocalDate date;
    @NonNull
    private Set<ExerciseMongo> exerciseMongo;
}
