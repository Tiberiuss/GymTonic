package com.gym.GymTonic.model.mongo;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;


import java.util.List;
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
    @DBRef
    private UserModel user;
    @NonNull
    private LocalDate date;
    @NonNull
    @DBRef
    private List<ExerciseMongo> exercise;
}
