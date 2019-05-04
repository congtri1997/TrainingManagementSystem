package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hcmuaf.tms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
