package com.josfhat.com.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.josafhat.com.Repositories.UserInRoleRepository;
import com.josafhat.com.Repositories.UserRepository;
import com.josfhat.com.entities.User;
import com.josfhat.com.models.JosafhatSecurityRule;

//@JosafhatSecurityRule -> si quiere ponerle esta regla a todos los metodos de la clase
@Service
public class UserService {
	

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	
	//@Secured({"ROLE_ADMIN"})
//	@PreAuthorize("hasRole('ROLE_IT') or hasRole('ROLE_Admin')")
//	@PostAuthorize("hasRole('ROLE_Admin')")
	@JosafhatSecurityRule
	public List<User> getUsersByRole(String roleName) {
		System.out.println("Getting roles by name");
		return userInRoleRepository.findUsersByRoleName(roleName);
	}
	
	public Page<User> getUsers(int page, int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	public Page<String> getUsernames(int page, int size) {
		return userRepository.findUsernames(PageRequest.of(page, size));
	}
	
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}
		
	@Cacheable("users")
	public User getUserByUsername(String username) {
		
		System.out.println("Getting user");
		try {
			Thread.sleep(3000);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username , password).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}
	
	@CacheEvict("users")
	public void deleteUserByUsername(String username) {
		User user = this.getUserByUsername(username);
		this.userRepository.delete(user);
		
	}
	
	
}
