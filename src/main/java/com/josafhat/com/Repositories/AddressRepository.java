package com.josafhat.com.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	@Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
	public Optional<List<Address>> findAddressesByProfileIdAndUserId(Integer userId, Integer profileId);
}
