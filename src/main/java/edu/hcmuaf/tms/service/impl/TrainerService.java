package edu.hcmuaf.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.repository.AbstractUserRepository;
import edu.hcmuaf.tms.repository.RoleRepository;
import edu.hcmuaf.tms.repository.TrainerRepository;

@Service
@Transactional
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private AbstractUserRepository abstractUserRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public boolean isUsernameAlreadyExist(String username) {
		return abstractUserRepository.isUserNameAlreadyExisted(username);
	}

	public boolean isEmailAlreadyExist(String email) {
		return trainerRepository.isEmailAlreadyExisted(email);
	}

	public void addTrainer(TrainerForm trainerForm) {

		Role role = roleRepository.findByName("ROLE_TRAINER");

		trainerRepository.save(trainerForm.doAddFirstTime(role, encoder));
	}

	public void updateTrainer(TrainerForm trainerForm) {
		Trainer trainer = trainerRepository.getOne(trainerForm.getId());
		trainerForm.update(trainer);
		trainerRepository.save(trainer);
	}

	public List<Trainer> listAll() {
		return trainerRepository.findAll();
	}

	public Trainer getOne(Long id) {
		return trainerRepository.getOne(id);
	}

}
