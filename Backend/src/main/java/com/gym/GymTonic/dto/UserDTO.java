package com.gym.GymTonic.dto;

import com.gym.GymTonic.model.mongo.UserModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {
    private String id;
    @NonNull
    private String username;
}
