package com.gym.GymTonic.model.mongo;

import com.gym.GymTonic.model.elastic.Exercise;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


import java.util.Set;

@Document
public class Routine {
    @Id
    private String id;
    private String name;
    private Integer user_id;
    private LocalDate date;

    private Set<Exercise> exercises;

    public Routine() {
    }

    public Routine(String name, Integer user_id, LocalDate date) {
        this.name = name;
        this.user_id = user_id;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
