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
    private Integer muscle_group_id;

    public Muscle(String muscle_name, Integer muscle_group_id){
        this.muscle_name = muscle_name;
        this.muscle_group_id = muscle_group_id;
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

    public Integer getMuscle_group_id() {
        return muscle_group_id;
    }

    public void setMuscle_group_id(Integer muscle_group_id) {
        this.muscle_group_id = muscle_group_id;
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "id=" + id +
                ", muscle_name='" + muscle_name + '\'' +
                ", muscle_group_id=" + muscle_group_id +
                '}';
    }
}
