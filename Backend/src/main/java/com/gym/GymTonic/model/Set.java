package com.gym.GymTonic.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "set")
public class Set {
    @Id
    private String id;
    private Integer number;
    private Integer reps;
    private Float weight;
    

    private Routine routine;


    private Exercise exercise;

    public Set() {
    }

    public Set(Integer number, Integer reps, Float weight, Routine routine, Exercise exercise) {
        this.number = number;
        this.reps = reps;
        this.weight = weight;
        this.routine = routine;
        this.exercise = exercise;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getReps() {
        return this.reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Float getWeight() {
        return this.weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

}
