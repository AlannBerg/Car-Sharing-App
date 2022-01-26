package com.example.CarRentalAplication;

import com.example.CarRentalAplication.Repositories.ClientSECURITYREPOSITORY;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CarRentalAplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalAplicationApplication.class, args);
	}

}
