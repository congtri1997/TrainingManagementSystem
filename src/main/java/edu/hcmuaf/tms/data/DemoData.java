package edu.hcmuaf.tms.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.hcmuaf.tms.entity.Admin;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.repository.AbstractUserRepository;
import edu.hcmuaf.tms.repository.impl.UserCredentialDAO;
import edu.hcmuaf.tms.service.impl.AdminService;
import edu.hcmuaf.tms.service.impl.RoleService;

@Component
public class DemoData {

//	@Autowired
//	private final EntityRepository repo;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminService adminService;

	@Autowired
	UserCredentialDAO UserCredentialDAO;

	@Autowired
	AbstractUserRepository abstractUserRepository;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {

//        repo.save(new Entity(...));
		roleService.add(new Role("ROLE_ADMIN"));
		roleService.add(new Role("ROLE_STAFF"));
		roleService.add(new Role("ROLE_TRAINER"));
		roleService.add(new Role("ROLE_TRAINEE"));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Admin admin = new Admin("admin", encoder.encode("admin"), true);
		adminService.add(admin, "ROLE_STAFF");

	}
}