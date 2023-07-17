package com.gym.GymTonic.model.elastic;

import com.gym.GymTonic.model.Exercise;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "exercise")
public class ExerciseElastic extends Exercise {
}
