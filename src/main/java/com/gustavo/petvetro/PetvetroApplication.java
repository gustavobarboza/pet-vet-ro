package com.gustavo.petvetro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PetvetroApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("senha"));
		SpringApplication.run(PetvetroApplication.class, args);

	}

}
