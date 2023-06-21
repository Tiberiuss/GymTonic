package com.gym.GymTonic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
@Table(name = "exercise_table")
public class Exercise {

    @Id
    @SequenceGenerator(
        name = "exercise_sequence",
        sequenceName = "exercise_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "exercise_sequence"
    )
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Material material;

    public Exercise() {
    }

    public Exercise(String name, Material material) {
        this.name = name;
        this.material = material;
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

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", material='" + getMaterial() + "'" +
            "}";
    }

}
