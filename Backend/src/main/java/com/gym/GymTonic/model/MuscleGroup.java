package com.gym.GymTonic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Collection;

@Document(indexName = "muscle_group")
public class MuscleGroup {
    @Id
    private String id;
    private String category;
    private Collection<Muscle> muscles;
    public MuscleGroup(){}
    public MuscleGroup(String category){
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
