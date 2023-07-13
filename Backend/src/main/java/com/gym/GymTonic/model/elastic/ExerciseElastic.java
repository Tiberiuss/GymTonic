package com.gym.GymTonic.model.elastic;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import java.util.Collection;


@Document(indexName = "exercise")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class ExerciseElastic {

    @Id
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