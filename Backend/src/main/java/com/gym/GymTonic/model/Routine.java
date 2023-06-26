package com.gym.GymTonic.model;

import java.time.LocalDate;


import java.util.Set;

//@Entity
//@Table(name = "routine_table")
public class Routine {


    private Integer id;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
