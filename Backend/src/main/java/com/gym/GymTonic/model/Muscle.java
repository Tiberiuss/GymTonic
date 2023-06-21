package com.gym.GymTonic.model;

import javax.persistence.*;

@Entity
@Table(name = "muscle")
public class Muscle {
    @Id
    @SequenceGenerator(
            name = "muscle_sequence",
            sequenceName = "muscle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "muscle_sequence"
    )
    private Integer id;
    private String muscle_name;
    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroup muscle_group;

    public Muscle(String muscle_name, MuscleGroup muscle_group){
        this.muscle_name = muscle_name;
        this.muscle_group = muscle_group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
