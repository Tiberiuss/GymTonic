package com.gym.GymTonic.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Workout {
    @Id
    private String id;

    @DBRef
    private Routine routine;

    @DocumentReference
    private UserModel user;

    private LocalDate date;
    private List<Set> set;
}
