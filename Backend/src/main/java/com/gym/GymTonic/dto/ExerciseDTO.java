package com.gym.GymTonic.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import lombok.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseDTO {
    @JsonView(Views.CreateWorkout.class)
    private String id;
    @JsonView(Views.Private.class)
    private String name;
    @JsonView(Views.Private.class)
    private Material material;
    @JsonView(Views.Private.class)
    private Collection<Muscle> muscle;
    @JsonView(Views.Private.class)
    private Collection<String> video;
}
