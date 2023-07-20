package com.gym.GymTonic.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gym.GymTonic.model.mongo.Routine;
import com.gym.GymTonic.model.mongo.Set;
import com.gym.GymTonic.model.mongo.UserModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutDTO {
    @JsonView(Views.Private.class)
    private String id;
    @JsonView(Views.CreateWorkout.class)
    private RoutineDTO routine;
    @JsonView(Views.Private.class)
    private UserDTO user;
    @JsonView(Views.CreateWorkout.class)
    private LocalDate date;
    @JsonView(Views.CreateWorkout.class)
    private List<SetDTO> set;
}
