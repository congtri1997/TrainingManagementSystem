package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.service.impl.TrainerService;

@Component
public class TrainerUpdateValidator implements Validator {

	@Autowired
	private TrainerService trainerService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TrainerForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		TrainerForm trainerForm = (TrainerForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.trainerForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.trainerForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.trainerForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingPlace", "NotEmpty.trainerForm.workingPlace");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingType", "NotEmpty.trainerForm.workingType");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty.trainerForm.phoneNumber");
		if (trainerForm.getId() == null ||!trainerService.existsById(trainerForm.getId())) {
			errors.rejectValue("id", "NotExist.trainerForm.id");
		}
	}

}
