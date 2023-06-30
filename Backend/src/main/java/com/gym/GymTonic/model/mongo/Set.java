package com.gym.GymTonic.model.mongo;


import com.gym.GymTonic.model.elastic.Exercise;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Set {
    @Id
    private String id;
    @NonNull
    private Integer number;
    @NonNull
    private Integer reps;
    @NonNull
    private Float weight;
    @NonNull
    private Routine routine;
    @NonNull
    private Exercise exercise;

}
