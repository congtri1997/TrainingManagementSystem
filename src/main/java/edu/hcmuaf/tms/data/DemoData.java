//package edu.hcmuaf.tms.data;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import edu.hcmuaf.tms.entity.Admin;
//import edu.hcmuaf.tms.entity.ProgrammingLanguage;
//import edu.hcmuaf.tms.entity.Role;
//import edu.hcmuaf.tms.entity.WorkingType;
//import edu.hcmuaf.tms.repository.AbstractUserRepository;
//import edu.hcmuaf.tms.repository.ProgrammingLanguagRepository;
//import edu.hcmuaf.tms.repository.impl.UserCredentialDAO;
//import edu.hcmuaf.tms.service.impl.AdminService;
//import edu.hcmuaf.tms.service.impl.ProgrammingLanguageService;
//import edu.hcmuaf.tms.service.impl.RoleService;
//import edu.hcmuaf.tms.service.impl.WorkingTypeService;
//
//@Component
//public class DemoData {
//
////	@Autowired
////	private final EntityRepository repo;
//	@Autowired
//	private RoleService roleService;
//	@Autowired
//	private AdminService adminService;
//
//	@Autowired
//	UserCredentialDAO UserCredentialDAO;
//
//	@Autowired
//	AbstractUserRepository abstractUserRepository;
//
//	@Autowired
//	ProgrammingLanguageService programmingLanguageService;
//
//	@Autowired
//	WorkingTypeService workingTypeService;
//
//	@EventListener
//	public void appReady(ApplicationReadyEvent event) {
//
////        repo.save(new Entity(...));
//		initialRole();
//		
//
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////
////		Admin admin = new Admin("admin", encoder.encode("admin"), true);
////		adminService.add(admin, "ROLE_STAFF");
//
//		initialProgrammingLanguage();
//
//		initialWorkingType();
//	}
//
//	private void initialRole() {
//		if (!roleService.isRoleAlreadyExist("ROLE_ADMIN")) {
//			roleService.add(new Role("ROLE_ADMIN"));
//		}
//
//		if (!roleService.isRoleAlreadyExist("ROLE_STAFF")) {
//			roleService.add(new Role("ROLE_STAFF"));
//		}
//		
//		if (!roleService.isRoleAlreadyExist("ROLE_TRAINER")) {
//			roleService.add(new Role("ROLE_TRAINER"));
//		}
//		
//		if (!roleService.isRoleAlreadyExist("ROLE_TRAINEE")) {
//			roleService.add(new Role("ROLE_TRAINEE"));
//		}
//	}
//
//	private void initialWorkingType() {
//		if (!workingTypeService.isWorkingTypeAlreadyExist("internal")) {
//			workingTypeService.add(new WorkingType("internal"));
//		}
//		if (!workingTypeService.isWorkingTypeAlreadyExist("external")) {
//			workingTypeService.add(new WorkingType("external"));
//		}
//	}
//
//	private void initialProgrammingLanguage() {
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("java")) {
//			programmingLanguageService.add(new ProgrammingLanguage("java"));
//		}
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("c")) {
//			programmingLanguageService.add(new ProgrammingLanguage("c"));
//		}
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("c#")) {
//			programmingLanguageService.add(new ProgrammingLanguage("c#"));
//		}
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("javascript")) {
//			programmingLanguageService.add(new ProgrammingLanguage("javascript"));
//		}
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("python")) {
//			programmingLanguageService.add(new ProgrammingLanguage("python"));
//		}
//		if (!programmingLanguageService.isProgrammingLanguageAlreadyExist("php")) {
//			programmingLanguageService.add(new ProgrammingLanguage("php"));
//		}
//	}
//}