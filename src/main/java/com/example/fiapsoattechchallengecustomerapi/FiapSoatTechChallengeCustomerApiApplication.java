package com.example.fiapsoattechchallengecustomerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FiapSoatTechChallengeCustomerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiapSoatTechChallengeCustomerApiApplication.class, args);
	}

}
