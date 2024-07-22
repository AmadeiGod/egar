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
		/*
		User user = new User();
        for(int i = 0; i < 10; i++){
            Random random = new Random();
            user.setLogin(""+ random.nextInt(1000));
            user.setPassword(""+random.nextInt(1000));
            user.setYear(random.nextInt(1000));
            userServices.save(user);
        }
		 */

	}

}
