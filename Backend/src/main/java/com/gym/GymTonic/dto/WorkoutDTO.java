package com.gym.GymTonic.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
