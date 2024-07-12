package ru.egartech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PerfectApplication {


	public static void main(String[] args) {
		SpringApplication.run(PerfectApplication.class, args);
		Date date = new Date();
		date.getTime();
		date.getHours();
	}
}
