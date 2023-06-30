package com.gym.GymTonic.model.mongo;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Collection;


@Document
public class Exercise {

    @Id
    private String id;
    private String name;
    private Material material;

    private Collection<Muscle> muscle;

    public Exercise() {
    }

    public Exercise(String name, Material material, Collection<Muscle> muscle) {
        this.name = name;
        this.material = material;
        this.muscle = muscle;
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

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Collection<Muscle> getMuscle() {
        return this.muscle;
    }

    public void setMuscle(Collection<Muscle> muscle) {
        this.muscle = muscle;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", material='" + getMaterial() + "'" +
            ", muscle='" + getMuscle() + "'" +
            "}";
    }

}
