package com.josfhat.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.javafaker.Faker;
import com.josafhat.com.Repositories.UserRepository;
import com.josfhat.com.entities.User;

@SpringBootApplication
@EnableJpaRepositories("com.josafhat.com.Repositories")
public class DemoApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for(int i = 0 ; i < 100000 ; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			user.setProfile(null);
			repository.save(user);
		}
		
	}

}
