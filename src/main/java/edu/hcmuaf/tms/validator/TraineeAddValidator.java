package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.service.impl.TraineeService;

@Component
public class TraineeAddValidator implements Validator {

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.traineeForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.traineeForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.traineeForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.traineeForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scoreOfToeic", "NotEmpty.traineeForm.scoreOfToeic");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "detailsOfExp", "NotEmpty.traineeForm.detailsOfExp");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "NotEmpty.traineeForm.department");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.traineeForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education", "NotEmpty.traineeForm.education");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "programmingLanguage", "NotEmpty.traineeForm.programmingLanguage");
		if (!errors.hasErrors()) {
			if (!traineeForm.getConfirmPassword().equals(traineeForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.traineeForm.confirmPassword");
			}

			boolean isUsernameExist = traineeService.isUsernameAlreadyExist(traineeForm.getUserName());
			if (isUsernameExist) {
				errors.rejectValue("userName", "Duplicate.traineeForm.userName");
			}
		}
	}

}
