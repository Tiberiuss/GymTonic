package com.gym.GymTonic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class GymTonicApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymTonicApplication.class, args);
	}
}
