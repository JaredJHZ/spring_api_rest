package com.josafhat.com.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername (String username);
	public Optional<User> findByUsernameAndPassword (String username, String password);
	
	
	// No es SQL se llama JPQL
	@Query("SELECT u.username FROM User u")
	public Page<String> findUsernames(Pageable pageable);
}
