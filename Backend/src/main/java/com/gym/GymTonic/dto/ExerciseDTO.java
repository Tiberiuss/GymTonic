package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class ExerciseDTO {

    private String id;
    @NonNull
    private String name;
    @NonNull
    private Material material;
    @NonNull
    private Collection<Muscle> muscle;
    @NonNull
    private Collection<String> video;
}
