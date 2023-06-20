package com.gym.GymTonic;

import com.gym.GymTonic.helper.CSVHelper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymTonicApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(GymTonicApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CSVHelper.initDB();
	}
}
