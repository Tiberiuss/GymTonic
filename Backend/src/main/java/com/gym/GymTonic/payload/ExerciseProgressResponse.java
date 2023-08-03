package com.gym.GymTonic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseProgressResponse {
    private LocalDate date;
    private float weight;
}
