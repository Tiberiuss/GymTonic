package com.gym.GymTonic.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.model.mongo.UserModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoutineDTO {
    @JsonView(Views.CreateWorkout.class)
    private String id;
    @JsonView(Views.CreateWorkout.class)
    private String name;
    @JsonView(Views.Private.class)
    private UserDTO user;
    @JsonView(Views.Private.class)
    private LocalDate date;
    @JsonView(Views.CreateWorkout.class)
    private List<ExerciseDTO> exercise;
}
