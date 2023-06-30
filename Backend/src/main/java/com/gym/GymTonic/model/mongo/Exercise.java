package com.gym.GymTonic.model.mongo;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Collection;


@Document
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Exercise {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Material material;
    @NonNull
    private Collection<Muscle> muscle;

}
