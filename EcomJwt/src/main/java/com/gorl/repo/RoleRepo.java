package com.gorl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);
}
