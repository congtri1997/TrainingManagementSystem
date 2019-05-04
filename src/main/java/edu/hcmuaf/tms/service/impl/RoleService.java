package edu.hcmuaf.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public void add(Role role) {
		roleRepository.save(role);
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
