package com.josfhat.com.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.josafhat.com.Repositories.UserInRoleRepository;
import com.josafhat.com.Repositories.UserRepository;
import com.josfhat.com.entities.User;
import com.josfhat.com.entities.UserInRole;

@Service
public class JaredUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = this.userRepository.findByUsername(username);
		System.out.println("oa");
		if (optional.isPresent()) {
			System.out.println("xd");
			User user = optional.get();
			Optional<List<UserInRole>> optionalUsersInRole = this.userInRoleRepository.findAllByUserId(user.getId());
			List<UserInRole> userInRoles = optionalUsersInRole.get();
			String[] roles = userInRoles.stream().map(r -> r.getRole().getName()).toArray(String[]::new);
			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		}else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	
}
