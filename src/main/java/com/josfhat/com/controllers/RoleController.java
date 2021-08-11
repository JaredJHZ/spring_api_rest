package com.josfhat.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.josfhat.com.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<List<Role>> getRoles() {
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
	
}
