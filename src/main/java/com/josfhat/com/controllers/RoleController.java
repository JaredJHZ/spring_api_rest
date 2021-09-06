package com.josfhat.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josfhat.com.entities.Role;
import com.josfhat.com.entities.User;
import com.josfhat.com.entities.UserInRole;
import com.josfhat.com.services.RoleService;
import com.josfhat.com.services.UserInRoleServices;
import com.josfhat.com.services.UserService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@Autowired
	private UserInRoleServices userInRoleService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<Role>> getRoles() {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth: "+authentication.getName());
		System.out.println("Auth: "+authentication.getPrincipal());
		System.out.println("Auth: "+authentication.getCredentials());
		System.out.println("Auth: "+authentication.getAuthorities().toString());
		return new ResponseEntity<List<Role>>(service.getRoles(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> createRoles ( @RequestBody Role role ) {
		return new ResponseEntity<Role>(service.createRole(role), HttpStatus.CREATED);
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole (@PathVariable("roleId") Integer id, @RequestBody Role role) {
		return new ResponseEntity<Role>(service.updateRole(id, role), HttpStatus.OK);
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer id) {
		service.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{roleId}/user/{userId}")
	public ResponseEntity<UserInRole> addRoleToUser(
			@PathVariable("roleId") Integer roleId,
			@PathVariable("userId") Integer userId,
			@RequestBody UserInRole userInRole
			) { 
			
		return new ResponseEntity<UserInRole>(
			this.userInRoleService.addRoleToUser(roleId, userId, userInRole),
			HttpStatus.CREATED
		);
		
	}
	
//	@GetMapping("/{roleId}/users")
//	public ResponseEntity<List<UserInRole>> getAllUsersInRole(@PathVariable("roleId") Integer id) {
//		return new ResponseEntity<List<UserInRole>>(this.userInRoleService.getAllUsersInRole(id), HttpStatus.OK);
//	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<UserInRole>> getAllRolesInUser(
			@PathVariable("userId") Integer userId ){
		return new ResponseEntity<List<UserInRole>>( this.userInRoleService.getAllRolesInUser(userId) , HttpStatus.OK);
	}
	
	@GetMapping("/{roleName}/users")
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable("roleName") String roleName) {
		return new ResponseEntity<List<User>>(this.userService.getUsersByRole(roleName), HttpStatus.OK);
	}
	
}