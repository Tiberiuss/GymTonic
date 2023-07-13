package com.gym.GymTonic.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Document
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Workout {
    @Id
    private String id;
    @NonNull
    @DBRef
    private Routine routine;
    @NonNull
    private LocalDate date;
    @NonNull
    private List<Set> set;
}
