package edu.hcmuaf.tms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.entity.WorkingType;
import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.repository.AbstractUserRepository;
import edu.hcmuaf.tms.repository.TrainerRepository;

@Service
@Transactional
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private AbstractUserRepository abstractUserRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private WorkingTypeService workingTypeService;

	public boolean isUsernameAlreadyExist(String username) {
		return abstractUserRepository.isUserNameAlreadyExisted(username);
	}

	public boolean isEmailAlreadyExist(String email) {
		return trainerRepository.isEmailAlreadyExisted(email);
	}

	public void addTrainer(TrainerForm trainerForm) {
		if (trainerForm == null)
			return;
		roleService.addIfNotExist("ROLE_TRAINER");

		Role role = roleService.findByName("ROLE_TRAINER");

		Trainer trainer = new Trainer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainer.setEmail(trainerForm.getEmail());
		trainer.setEnabled(true);
		trainer.setBirthDate(LocalDate.parse(trainerForm.getBirthDate(), formatter));
		trainer.setEncryptedPassword(encoder.encode(trainerForm.getPassword()));
		trainer.setFirstName(trainerForm.getFirstName());
		trainer.setLastName(trainerForm.getLastName());
		trainer.setPhoneNumber(trainerForm.getPhoneNumber());
		trainer.setUserName(trainerForm.getUserName());
		trainer.setWorkingPlace(trainerForm.getWorkingPlace());
		try {
			Long workingTypeId = Long.parseLong(trainerForm.getWorkingType());
			WorkingType workingType = workingTypeService.findById(workingTypeId);
			if (workingType != null) {
				trainer.setWorkingType(workingType);
			}
		} catch (NumberFormatException e) {
		}
		trainer.getRoles().add(role);

		trainerRepository.save(trainer);
	}

	public void updateTrainer(TrainerForm trainerForm) {
		Trainer trainer = trainerRepository.getOne(trainerForm.getId());
//		trainerForm.update(trainer);
		if (trainer != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			trainer.setBirthDate(LocalDate.parse(trainerForm.getBirthDate(), formatter));
			trainer.setEmail(trainerForm.getEmail());
			trainer.setFirstName(trainerForm.getFirstName());
			trainer.setLastName(trainerForm.getLastName());
			trainer.setPhoneNumber(trainerForm.getPhoneNumber());
			trainer.setWorkingPlace(trainerForm.getWorkingPlace());
			try {
				Long workingTypeId = Long.parseLong(trainerForm.getWorkingType());
				WorkingType workingType = workingTypeService.findById(workingTypeId);
				if (workingType != null) {
					trainer.setWorkingType(workingType);
				}
			} catch (NumberFormatException e) {
			}
			trainerRepository.save(trainer);
		}
	}

	public List<Trainer> listAll() {
		return trainerRepository.findAll();
	}

	public Trainer getOne(Long id) {
		if (!trainerRepository.existsById(id))
			return null;
		Trainer trainer = trainerRepository.getOne(id);
		return trainer;
	}

	public void changePassword(TrainerForm trainerForm) {
		Trainer trainer = trainerRepository.getOne(trainerForm.getId());
		if (trainer != null) {
			trainer.setEncryptedPassword(encoder.encode(trainerForm.getPassword()));
			trainerRepository.save(trainer);
		}

	}

	public void delete(long id) {
		if (trainerRepository.existsById(id)) {
			trainerRepository.delete(trainerRepository.getOne(id));
		}
	}

	public boolean existsById(long id) {
		return trainerRepository.existsById(id);
	}

	public DataTablesOutput<TrainerForm> findAll(DataTablesInput input) {
		return trainerRepository.findAll(input,null,null,(Trainer t)  -> TrainerForm.toDTO(t));
	}

}
