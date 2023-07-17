package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.model.mongo.UserModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoutineDTO {
    private String id;
    private String name;
    private UserDTO user;
    private LocalDate date;
    private List<ExerciseDTO> exercise;
}
