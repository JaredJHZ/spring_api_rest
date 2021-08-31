package com.josfhat.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.josafhat.com.Repositories.RoleRepository;
import com.josfhat.com.entities.Role;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role createRole(Role role) {
		return repository.save(role);
	}
	
	public Role getRoleById(Integer roleId) {
		return repository.findById(roleId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No role found") );
	}
	
	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = repository.findById(roleId);
		if(result.isPresent()) {
			return repository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No role found");
		}
	}
	public void deleteRole(Integer roleId) {
		Optional<Role> result = repository.findById(roleId);
		if(result.isPresent()) {
			repository.deleteById(roleId);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No role found");
		}
	}
}
