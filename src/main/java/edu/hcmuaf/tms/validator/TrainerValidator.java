package edu.hcmuaf.tms.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.service.impl.TrainerService;

@Component
public class TrainerValidator implements Validator {
	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private TrainerService trainerService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TrainerForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		TrainerForm trainerForm = (TrainerForm) target;
		boolean isAdd = trainerForm.getId() == null;
		System.out.println("Dô");
		if (isAdd) {
			System.out.println("add");
			validateAdd(trainerForm, errors);
		} else {
			System.out.println("Update");
			validateUpdate(trainerForm, errors);
		}
	}

	private void validateUpdate(TrainerForm trainerForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.trainerForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.trainerForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.trainerForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingPlace", "NotEmpty.trainerForm.workingPlace");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingType", "NotEmpty.trainerForm.workingType");
		
	}

	private void validateAdd(TrainerForm trainerForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.trainerForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.trainerForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.trainerForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.trainerForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.trainerForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.trainerForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.trainerForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingPlace", "NotEmpty.trainerForm.workingPlace");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingType", "NotEmpty.trainerForm.workingType");
		if (!errors.hasErrors()) {
			if (!trainerForm.getConfirmPassword().equals(trainerForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.trainerForm.confirmPassword");
			}

			if (!this.emailValidator.isValid(trainerForm.getEmail())) {
				// Email không hợp lệ.
				errors.rejectValue("email", "Pattern.trainerForm.email");
			} else if (trainerForm.getId() == null) {
				/* AppUser dbUser = appUserDAO.findAppUserByEmail(appUserForm.getEmail()); */
				boolean isEmailExist = trainerService.isEmailAlreadyExist(trainerForm.getEmail());
				if (isEmailExist) {
					// Email đã được sử dụng bởi tài khoản khác.
					errors.rejectValue("email", "Duplicate.trainerForm.email");
				}
			}

			boolean isUsernameExist = trainerService.isUsernameAlreadyExist(trainerForm.getUserName());
			if (isUsernameExist) {
				errors.rejectValue("userName", "Duplicate.trainerForm.userName");
			}

		}

	}
}
