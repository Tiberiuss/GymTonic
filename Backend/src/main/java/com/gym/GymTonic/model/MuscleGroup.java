package com.gym.GymTonic.model;

import java.util.Collection;

//@Entity
//@Table(name = "muscle_group")
public class MuscleGroup {

    private Integer id;
    private String category;
    private Collection<Muscle> muscles;
    public MuscleGroup(){}
    public MuscleGroup(String category){
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
