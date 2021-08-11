package com.josafhat.com.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.josfhat.com.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
