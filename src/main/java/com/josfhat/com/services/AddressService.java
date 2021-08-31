package com.josfhat.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Address;
import com.josafhat.com.Repositories.AddressRepository;
import com.josafhat.com.Repositories.ProfileRepository;
import com.josfhat.com.entities.Profile;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ProfileRepository profileRepository;

	public List<com.josfhat.com.entities.Address> findAddressesByProfileIdAndUserId(Integer userId, Integer profileId) {
		return this.addressRepository.findAddressesByProfileIdAndUserId(userId, profileId).orElseThrow(
				()->  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format (" Not addresses where found to user  %d", userId)  )  );
	}

	public com.josfhat.com.entities.Address createAddress(Integer userId, Integer profileId,
			com.josfhat.com.entities.Address address) {
		
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		
		if(result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found %d", userId));
		}
		
	}
}
