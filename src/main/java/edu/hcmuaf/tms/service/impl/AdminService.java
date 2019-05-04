package edu.hcmuaf.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Admin;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.repository.AdminRepository;
import edu.hcmuaf.tms.repository.RoleRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void add(Admin admin, String role) {
		Role roleAdmin = roleRepository.findByName(role);
		admin.getRoles().add(roleAdmin);
		adminRepository.save(admin);
	}

}
