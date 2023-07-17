package com.gym.GymTonic.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
