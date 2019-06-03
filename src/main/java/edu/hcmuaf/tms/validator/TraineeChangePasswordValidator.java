package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.service.impl.TraineeService;

@Component
public class TraineeChangePasswordValidator implements Validator {
	@Autowired
	private TraineeService traineeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TraineeForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		TraineeForm traineeForm = (TraineeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.traineeForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.traineeForm.confirmPassword");
		if (traineeForm.getId() == null ||!traineeService.existsById(traineeForm.getId())) {
			errors.rejectValue("id", "NotExist.traineeForm.id");
		}
		if (!errors.hasErrors()) {
			if (!traineeForm.getConfirmPassword().equals(traineeForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.traineeForm.confirmPassword");
			}
		}
		
		
	}

}
