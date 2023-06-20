package com.gym.GymTonic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public Test() {
    }

    public Test(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
