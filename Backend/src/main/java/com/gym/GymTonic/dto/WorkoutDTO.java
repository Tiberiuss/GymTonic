package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.model.mongo.Set;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutDTO {
    private String id;
    private RoutineDTO routine;
    private LocalDate date;
    private List<Set> set;
}
