package ru.egartech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaAuditing
@EnableCaching
@EnableJpaRepositories
@SpringBootApplication
public class PerfectApplication {
	public static void main(String[] args) {

		SpringApplication.run(PerfectApplication.class, args);

	}

}
