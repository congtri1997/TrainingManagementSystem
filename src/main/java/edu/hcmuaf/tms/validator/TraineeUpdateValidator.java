package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.service.impl.TraineeService;

@Component
public class TraineeUpdateValidator implements Validator {

	@Autowired
	private TraineeService traineeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TraineeForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		TraineeForm traineeForm = (TraineeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.traineeForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.traineeForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.traineeForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scoreOfToeic", "NotEmpty.traineeForm.scoreOfToeic");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "detailsOfExp", "NotEmpty.traineeForm.detailsOfExp");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "NotEmpty.traineeForm.department");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.traineeForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education", "NotEmpty.traineeForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "programmingLanguage", "NotEmpty.traineeForm.programmingLanguage");
		if (traineeForm.getId() == null ||!traineeService.existsById(traineeForm.getId())) {
			errors.rejectValue("id", "NotExist.traineeForm.id");
		}
	}

}
