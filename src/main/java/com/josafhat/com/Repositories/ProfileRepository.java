package com.josafhat.com.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer>{

}
