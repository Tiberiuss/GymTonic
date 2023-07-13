package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.model.mongo.Set;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class WorkoutDTO {
    private String id;
    @NonNull
    private RoutineDTO routine;
    @NonNull
    private LocalDate date;
    @NonNull
    private List<Set> set;
}
