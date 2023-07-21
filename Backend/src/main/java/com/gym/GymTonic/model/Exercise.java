package com.gym.GymTonic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exercise {
    @Id
    private String id;
    private String name;
    private Material material;
    private Collection<Muscle> muscle;
    private Collection<String> video;
    private List<String> steps;
}
