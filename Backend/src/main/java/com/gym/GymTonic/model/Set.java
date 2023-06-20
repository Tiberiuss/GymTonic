package com.gym.GymTonic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "set_table")
public class Set {
    @Id
    @SequenceGenerator(
        name = "set_sequence",
        sequenceName = "set_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "set_sequence"
    )
    private Integer id;
    private Integer number;
    private Integer reps;
    private Float weight;
    
    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
