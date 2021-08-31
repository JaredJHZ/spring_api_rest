package com.josfhat.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.josafhat.com.Repositories.UserInRoleRepository;
import com.josfhat.com.entities.Role;
import com.josfhat.com.entities.User;
import com.josfhat.com.entities.UserInRole;

@Service
public class UserInRoleServices {

	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public List<UserInRole> getAllUsersInRole(Integer roleId) {
		return this.userInRoleRepository.findAllByRoleId(roleId).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
	}
	
	public List<UserInRole> getAllRolesInUser(Integer userId) {
		return this.userInRoleRepository.findAllByUserId(userId).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The user %d does not have any role assigned", userId))
		);
	}
	
	public UserInRole addRoleToUser(Integer roleId , Integer userId, UserInRole userInRole) {
		
		User user = userService.getUserById(userId);
		
		Role role = roleService.getRoleById(roleId);
		
		userInRole.setRole(role);
		
		userInRole.setUser(user);
		
		return this.userInRoleRepository.save(userInRole);
		
		
		
		
	}
	
}
