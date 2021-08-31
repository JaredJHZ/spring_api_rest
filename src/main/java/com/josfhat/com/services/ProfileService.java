package com.josfhat.com.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.josafhat.com.Repositories.ProfileRepository;
import com.josafhat.com.Repositories.UserRepository;
import com.josfhat.com.entities.Profile;
import com.josfhat.com.entities.User;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Profile create(Integer userId, Profile profile) {
		Optional<User> result = userRepository.findById(userId);
		if (result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("User {%d} not found ", userId));
		}
	}
	
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId, profileId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("Profile not found for {%d} ", userId)));
	}
	
}
