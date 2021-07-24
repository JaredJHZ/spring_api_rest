package com.josfhat.com.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;

import com.josfhat.com.models.*;

@Service
public class UserServices {
	
	@Autowired
	private Faker faker;
	
	private List<User> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i = 0 ; i < 10 ; i++) {
			User user = new User(faker.name()
					.firstName() , faker.harryPotter().character(), faker.dragonBall().character());
			
			users.add(user);
		}
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public User getUserByUsername(String username) {
		return users.stream().filter( user -> user.getUsername().
				equals(username) )
				.findAny()
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	public User createUser(User user) {
		boolean userCreated = users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
		if(userCreated) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("user already created %s", user.getUsername()));
		}
		users.add(user);
		return user;
	}
	
	public User updateUser(String username, User user) {
		User userToUpdate = users.stream()
				.filter(u -> u.getUsername().equals(username))
				.findAny()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user to update"));
		userToUpdate.setNickname(user.getNickname());
		userToUpdate.setPassword(user.getPassword());
		return userToUpdate;
	}
	
	public void deleteUser(String username) {
		User userToBeDeleted = this.getUserByUsername(username);
		users.remove(userToBeDeleted);
		return "Deleted";
	}
	
}
