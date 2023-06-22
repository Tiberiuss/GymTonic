package com.gym.GymTonic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.util.Set;


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
    @ManyToMany
    @JoinTable(
        name = "exercise_muscle", 
        joinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "muscle_id", referencedColumnName = "id")
    )
    private Set<Muscle> muscle;

    public Exercise() {
    }

    public Exercise(String name, Material material, Set<Muscle> muscle) {
        this.name = name;
        this.material = material;
        this.muscle = muscle;
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

    public Set<Muscle> getMuscle() {
        return this.muscle;
    }

    public void setMuscle(Set<Muscle> muscle) {
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
