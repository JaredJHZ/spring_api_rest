package com.josfhat.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josfhat.com.models.User;
import com.josfhat.com.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserServices userService;
	
	@GetMapping
	public ResponseEntity<List<User>> get(@RequestParam(value="startsWith", required=false) String startsWith) {
		return new ResponseEntity<List<User>>(userService.getUsers(startsWith), HttpStatus.OK);
	}
	
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getByUsername( @PathVariable("username") String username ) {
		return new ResponseEntity<User>(userService.getUserByUsername(username) , HttpStatus.OK );
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<User> update(@PathVariable("username")String username,  @RequestBody User user) {
		System.out.println(username);
		return new ResponseEntity<User>(userService.updateUser(username, user), HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> delete(@PathVariable("username") String username) {
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
