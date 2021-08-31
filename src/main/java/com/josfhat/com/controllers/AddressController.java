package com.josfhat.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josfhat.com.entities.Address;
import com.josfhat.com.services.AddressService;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<com.josfhat.com.entities.Address>> findAddressesByProfileIdAndUserId(
			@PathVariable ("userId") Integer userId,
			@PathVariable ("profileId") Integer profileId
			) {
				return new ResponseEntity<List<com.josfhat.com.entities.Address>>(
							addressService.findAddressesByProfileIdAndUserId(userId, profileId),
							HttpStatus.OK
						);
						
		
	}
	
	@PostMapping
	public ResponseEntity<Address> create(@RequestBody Address address ,
			@PathVariable ("userId") Integer userId,
			@PathVariable ("profileId") Integer profileId) {
		return new ResponseEntity<Address>( addressService.createAddress(userId, profileId, address)  , HttpStatus.CREATED);
	}
}
