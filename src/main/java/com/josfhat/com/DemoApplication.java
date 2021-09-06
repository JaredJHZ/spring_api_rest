package com.josfhat.com;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.javafaker.Faker;
import com.josafhat.com.Repositories.RoleRepository;
import com.josafhat.com.Repositories.UserInRoleRepository;
import com.josafhat.com.Repositories.UserRepository;
import com.josfhat.com.entities.Role;
import com.josfhat.com.entities.User;
import com.josfhat.com.entities.UserInRole;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories("com.josafhat.com.Repositories")
public class DemoApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	private static final Logger log = org.slf4j.LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Role[] roles = {
			new Role("Admin"),
			new Role("Dev"),
			new Role("IT")
		};
		
		for(Role role : roles) {
			this.roleRepository.save(role);
		}
		
		
		
		for(int i = 0 ; i < 100 ; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			repository.save(user);
			
			Role selectedRole = this.roleRepository.getById(  (int) (Math.random() * roles.length + 1)  );
						
			UserInRole newUserInRole = new UserInRole();
			
			newUserInRole.setRole(selectedRole);
			
			newUserInRole.setUser(user);
			
			
			this.userInRoleRepository.save(newUserInRole);
			
			List<UserInRole> roleName = this.userInRoleRepository.findAllByUserId(user.getId()).get();
			
			var roleNames = roleName.stream().map(role -> role.getRole().getName()).collect(Collectors.toList());
			
			System.out.println("   User created :: " + user.getUsername() + "   User password :: "+user.getPassword()+ "   User role::" + roleNames );

			
		}
		
	}

}
