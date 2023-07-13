package com.gym.GymTonic;

import com.gym.GymTonic.model.Material;
import com.gym.GymTonic.model.Muscle;
import com.gym.GymTonic.model.mongo.ExerciseMongo;
import com.gym.GymTonic.model.mongo.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class GymTonicApplication {
	public static void main(String[] args) {
		SpringApplication.run(GymTonicApplication.class, args);
	}
}
