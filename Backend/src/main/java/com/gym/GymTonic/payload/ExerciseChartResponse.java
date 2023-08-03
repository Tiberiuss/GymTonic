package com.gym.GymTonic.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseChartResponse {
    private String exerciseName;
    private List<ExerciseProgressResponse> charts;
}
