package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import lombok.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseDTO {

    private String id;
    private String name;
    private Material material;
    private Collection<Muscle> muscle;
    private Collection<String> video;
}
