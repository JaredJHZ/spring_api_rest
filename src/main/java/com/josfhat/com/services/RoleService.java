package com.josfhat.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josafhat.com.Repositories.RoleRepository;
import com.josfhat.com.entities.Role;
import com.josfhat.com.models.AuditDetails;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role createRole(Role role) {
		Role roleCreated = repository.save(role);
		String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
		AuditDetails details = new AuditDetails(createdBy ,role.getName());
		try {
			kafkaTemplate.send("josafhat-topic", mapper.writeValueAsString(details));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return roleCreated;
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
