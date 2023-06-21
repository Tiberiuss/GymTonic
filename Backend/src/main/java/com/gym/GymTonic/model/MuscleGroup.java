package com.gym.GymTonic.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "muscle_group")
public class MuscleGroup {
    @Id
    @SequenceGenerator(
            name = "muscle_group_sequence",
            sequenceName = "muscle_group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "muscle_group_sequence"
    )
    private Integer id;
    private String group;
    @OneToMany(mappedBy = "muscle_group")
    private Collection<Muscle> muscles;

    public MuscleGroup(String group){
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id=" + id +
                ", group='" + group + '\'' +
                '}';
    }
}
