package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.service.impl.TrainerService;

@Component
public class TrainerChangePasswordValidator implements Validator {
	@Autowired
	private TrainerService trainerService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TrainerForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		TrainerForm trainerForm = (TrainerForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.trainerForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.trainerForm.confirmPassword");
		if (trainerForm.getId() == null ||!trainerService.existsById(trainerForm.getId())) {
			errors.rejectValue("id", "NotExist.trainerForm.id");
		}
		if (!errors.hasErrors()) {
			if (!trainerForm.getConfirmPassword().equals(trainerForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.trainerForm.confirmPassword");
			}
		}
		
		
	}

}
