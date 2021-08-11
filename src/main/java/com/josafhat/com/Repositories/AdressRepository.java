package com.josafhat.com.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.Address;

@Repository
public interface AdressRepository extends CrudRepository<Address, Integer> {

}
