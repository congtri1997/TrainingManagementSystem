package edu.hcmuaf.tms.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Staff;
import edu.hcmuaf.tms.form.StaffForm;
import edu.hcmuaf.tms.repository.AbstractUserRepository;
import edu.hcmuaf.tms.repository.StaffRepository;

@Service
@Transactional
public class StaffService {
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private AbstractUserRepository abstractUserRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public boolean isUsernameAlreadyExist(String username) {
		return abstractUserRepository.isUserNameAlreadyExisted(username);
	}

	public void addStaff(StaffForm staffForm) {
		if (staffForm == null)
			return;
		roleService.addIfNotExist("ROLE_STAFF");

		Role role = roleService.findByName("ROLE_STAFF");

		Staff staff = new Staff();
		staff.setEnabled(true);
		staff.setEncryptedPassword(encoder.encode(staffForm.getPassword()));
		staff.setUserName(staffForm.getUserName());
		staff.getRoles().add(role);
		staffRepository.save(staff);
	}

	public List<Staff> listAll() {
		return staffRepository.findAll();
	}

	public Staff getOne(Long id) {
		if (!staffRepository.existsById(id))
			return null;
		Staff staff = staffRepository.getOne(id);
		return staff;
	}

	public void changePassword(StaffForm staffForm) {
		Staff staff = staffRepository.getOne(staffForm.getId());
		if (staff != null) {
			staff.setEncryptedPassword(encoder.encode(staffForm.getPassword()));
			staffRepository.save(staff);
		}

	}

	public void delete(long id) {
		if (staffRepository.existsById(id)) {
			staffRepository.delete(staffRepository.getOne(id));
		}
	}

	public boolean existsById(long id) {
		return staffRepository.existsById(id);
	}

	public DataTablesOutput<StaffForm> findAll(@Valid DataTablesInput input) {
		return staffRepository.findAll(input, null, null, (Staff t) -> StaffForm.toDTO(t));
	}

}
