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
