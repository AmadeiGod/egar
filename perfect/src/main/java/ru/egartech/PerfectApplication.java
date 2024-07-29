package ru.egartech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.egartech.models.User;

@EnableJpaRepositories
@SpringBootApplication
public class PerfectApplication {
	public static void main(String[] args) {

		SpringApplication.run(PerfectApplication.class, args);

	}

}
