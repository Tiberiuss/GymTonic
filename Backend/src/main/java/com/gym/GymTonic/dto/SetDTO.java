package com.gym.GymTonic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetDTO {
    private Integer number;
    private Integer reps;
    private Float weight;
    private ExerciseDTO exerciseMongo;
}
