package edu.hcmuaf.tms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.ImportTraineeAccountDTO;
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
		if (traineeForm == null)
			return;
		roleService.addIfNotExist("ROLE_TRAINER");

		Role role = roleService.findByName("ROLE_TRAINEE");

		Trainee trainee = new Trainee();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainee.setEnabled(true);
		if (traineeForm.getBirthDate() != null) {
			trainee.setBirthDate(LocalDate.parse(traineeForm.getBirthDate(), formatter));
		}
		trainee.setEncryptedPassword(encoder.encode(traineeForm.getPassword()));
		trainee.setFirstName(traineeForm.getFirstName());
		trainee.setLastName(traineeForm.getLastName());
		trainee.setUserName(traineeForm.getUserName());
		if (traineeForm.getScoreOfToeic() != null)
			trainee.setScoreOfToeic(Integer.parseInt(traineeForm.getScoreOfToeic()));
		trainee.setAddress(traineeForm.getAddress());
		trainee.setDetailsOfExp(traineeForm.getDetailsOfExp());
		trainee.setDepartment(traineeForm.getDepartment());
		trainee.setEducation(traineeForm.getEducation());
		trainee.getRoles().add(role);
		if (traineeForm.getProgrammingLanguage() != null) {
			Long programmingLangeuageID = traineeForm.getProgrammingLanguage().getId();
			ProgrammingLanguage programmingLanguage = programmingLanguageService.findById(programmingLangeuageID);
			if (programmingLanguage != null) {
				trainee.setProgrammingLanguage(programmingLanguage);
			}
		}
		traineeRepository.save(trainee);
	}

	public void updateTrainee(TraineeForm traineeForm) {
		if (traineeRepository.existsById(traineeForm.getId())) {
			Trainee trainee = traineeRepository.getOne(traineeForm.getId());
//		traineeForm.update(trainee);
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
				Long programmingLangeuageID = traineeForm.getProgrammingLanguage().getId();
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
		if (trainee != null) {
			trainee.setEncryptedPassword(encoder.encode(traineeForm.getPassword()));
			traineeRepository.save(trainee);
		}

	}

	public void delete(long id) {
		if (traineeRepository.existsById(id)) {
			traineeRepository.delete(traineeRepository.getOne(id));
		}
	}

	public boolean existsById(long id) {
		return traineeRepository.existsById(id);
	}

	public DataTablesOutput<TraineeForm> findAllWithSpecification(@Valid DataTablesInput input) {
		return traineeRepository.findAll(input, new ScoreOfToeicSpecification(input), null,
				(Trainee t) -> TraineeForm.toDTO(t));
	}

	public DataTablesOutput<TraineeForm> findAll(@Valid DataTablesInput input) {
		return traineeRepository.findAll(input, null, null, (Trainee t) -> TraineeForm.toDTO(t));
	}

	private class ScoreOfToeicSpecification implements Specification<Trainee> {
		private static final long serialVersionUID = 1L;
		private final Integer minScore;
		private final Integer maxScore;

		ScoreOfToeicSpecification(DataTablesInput input) {
			String scoreFilter = input.getColumn("scoreOfToeic").getSearch().getValue();
			if (!StringUtils.hasText(scoreFilter)) {
				minScore = maxScore = null;
				return;
			}
			String[] bounds = scoreFilter.split(";");
			minScore = getValue(bounds, 0);
			maxScore = getValue(bounds, 1);
		}

		private Integer getValue(String[] bounds, int index) {
			if (bounds.length > index && StringUtils.hasText(bounds[index])) {
				try {
					return Integer.valueOf(bounds[index]);
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return null;
		}

		@Override
		public Predicate toPredicate(Root<Trainee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			Expression<Integer> scoreOfToeic = root.get("scoreOfToeic").as(Integer.class);
			System.out.println(minScore + " - " + maxScore);
			if (minScore != null && maxScore != null) {
				return criteriaBuilder.between(scoreOfToeic, minScore, maxScore);
			} else if (minScore != null) {
				return criteriaBuilder.greaterThanOrEqualTo(scoreOfToeic, minScore);
			} else if (maxScore != null) {
				return criteriaBuilder.lessThanOrEqualTo(scoreOfToeic, maxScore);
			} else {
				return criteriaBuilder.conjunction();
			}
		}
	}

	public List<Trainee> findTraineeNotInACourse(long id) {
		return traineeRepository.findTraineeNotInACourse(id);
	}

	public List<Trainee> findTraineeInACourse(long id) {
		return traineeRepository.findTraineeInACourse(id);
	}

	public void importAccount(ImportTraineeAccountDTO importTraineeAccounts) {
		for (TraineeForm trainee : importTraineeAccounts.getTraineeForms()) {
			addTrainee(trainee);
		}
	}

}
