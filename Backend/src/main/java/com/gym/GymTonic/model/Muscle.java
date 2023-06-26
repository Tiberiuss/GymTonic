package com.gym.GymTonic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "muscle")
public class Muscle {
    @Id
    private String id;
    private String muscle_name;
    private MuscleGroup muscle_group;

    public Muscle() {
    }

    public Muscle(String muscle_name, MuscleGroup muscle_group){
        this.muscle_name = muscle_name;
        this.muscle_group = muscle_group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMuscle_name() {
        return muscle_name;
    }

    public void setMuscle_name(String muscle_name) {
        this.muscle_name = muscle_name;
    }

    public MuscleGroup getMuscle_group() {
        return muscle_group;
    }

    public void setMuscle_group(MuscleGroup muscle_group) {
        this.muscle_group = muscle_group;
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "id=" + id +
                ", muscle_name='" + muscle_name + '\'' +
                ", muscle_group=" + muscle_group +
                '}';
    }
}
