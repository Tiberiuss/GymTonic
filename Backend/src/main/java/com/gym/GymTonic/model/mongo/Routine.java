package com.gym.GymTonic.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Routine {
    @Id
    private String id;
    private String name;

    @DBRef
    private UserModel user;

    private LocalDate date;

    @DBRef
    private List<ExerciseMongo> exercise;
}
