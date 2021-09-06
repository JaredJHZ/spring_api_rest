package com.josafhat.com.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.User;
import com.josfhat.com.entities.UserInRole;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer>  {
	
	public Optional<List<UserInRole>> findAllByRoleId(Integer roleId);
	public Optional<List<UserInRole>> findAllByUserId(Integer userId);
	
	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<User> findUsersByRoleName(String roleName);
	
}
