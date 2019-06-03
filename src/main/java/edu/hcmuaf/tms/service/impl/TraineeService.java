package edu.hcmuaf.tms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.repository.AbstractUserRepository;
import edu.hcmuaf.tms.repository.TraineeRepository;
@Service
@Transactional
public class TraineeService {
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private AbstractUserRepository abstractUserRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private ProgrammingLanguageService programmingLanguageService;

	public boolean isUsernameAlreadyExist(String username) {
		return abstractUserRepository.isUserNameAlreadyExisted(username);
	}


	public void addTrainee(TraineeForm traineeForm) {
		if(traineeForm == null) return;
		roleService.addIfNotExist("ROLE_TRAINER");

		Role role = roleService.findByName("ROLE_TRAINEE");

		Trainee trainee = new Trainee();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainee.setEnabled(true);
		trainee.setBirthDate(LocalDate.parse(traineeForm.getBirthDate(), formatter));
		trainee.setEncryptedPassword(encoder.encode(traineeForm.getPassword()));
		trainee.setFirstName(traineeForm.getFirstName());
		trainee.setLastName(traineeForm.getLastName());
		trainee.setUserName(traineeForm.getUserName());
		trainee.setScoreOfToeic(Integer.parseInt(traineeForm.getScoreOfToeic()));
		trainee.setAddress(traineeForm.getAddress());
		trainee.setDetailsOfExp(traineeForm.getDetailsOfExp());
		trainee.setDepartment(traineeForm.getDepartment());
		trainee.setEducation(traineeForm.getEducation());
		trainee.getRoles().add(role);
		try {
			Long programmingLangeuageID = Long.parseLong(traineeForm.getProgrammingLanguage());
			ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLangeuageID);
			if (programmingLanguage != null) {
				trainee.setProgrammingLanguage(programmingLanguage);
			}
		} catch (NumberFormatException e) {
		}
		traineeRepository.save(trainee);
	}

	public void updateTrainee(TraineeForm traineeForm) {
		Trainee trainee = traineeRepository.getOne(traineeForm.getId());
//		traineeForm.update(trainee);
		if (trainee != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			trainee.setBirthDate(LocalDate.parse(traineeForm.getBirthDate(), formatter));
			trainee.setFirstName(traineeForm.getFirstName());
			trainee.setLastName(traineeForm.getLastName());
			trainee.setScoreOfToeic(Integer.parseInt(traineeForm.getScoreOfToeic()));
			trainee.setAddress(traineeForm.getAddress());
			trainee.setDetailsOfExp(traineeForm.getDetailsOfExp());
			trainee.setDepartment(traineeForm.getDepartment());
			trainee.setEducation(traineeForm.getEducation());
			try {
				Long programmingLangeuageID = Long.parseLong(traineeForm.getProgrammingLanguage());
				ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLangeuageID);
				if (programmingLanguage != null) {
					trainee.setProgrammingLanguage(programmingLanguage);
				}
			} catch (NumberFormatException e) {
			}
			traineeRepository.save(trainee);
		}
	}

	public List<Trainee> listAll() {
		return traineeRepository.findAll();
	}

	public Trainee getOne(Long id) {
		if (!traineeRepository.existsById(id))
			return null;
		Trainee trainee = traineeRepository.getOne(id);
		return trainee;
	}

	public void changePassword(TraineeForm traineeForm) {
		Trainee trainee = traineeRepository.getOne(traineeForm.getId());
		if(trainee != null) {
			trainee.setEncryptedPassword(encoder.encode(traineeForm.getPassword()));
			traineeRepository.save(trainee);
		}
		
	}

	public void delete(long id) {
		if(traineeRepository.existsById(id)) {
			traineeRepository.delete(traineeRepository.getOne(id));
		}
	}
	
	public boolean existsById(long id) {
		return traineeRepository.existsById(id);
	}
}
