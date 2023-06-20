package com.gym.GymTonic.model;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @SequenceGenerator(
        name = "routine_sequence",
        sequenceName = "routine_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "routine_sequence"
    )
    private Integer id;
    private String name;
    private Integer user_id;
    private DayOfWeek date;

    public Routine(String name, Integer user_id, DayOfWeek date) {
        this.name = name;
        this.user_id = user_id;
        this.date = date;
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

    public Integer getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public DayOfWeek getDate() {
        return this.date;
    }

    public void setDate(DayOfWeek date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
