package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.StaffForm;
import edu.hcmuaf.tms.service.impl.StaffService;

@Component
public class StaffChangePasswordValidator implements Validator {
	@Autowired
	private StaffService staffService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == StaffForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		StaffForm staffForm = (StaffForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.staffForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.staffForm.confirmPassword");
		if (staffForm.getId() == null || !staffService.existsById(staffForm.getId())) {
			errors.rejectValue("id", "NotExist.staffForm.id");
		}
		if (!errors.hasErrors()) {
			if (!staffForm.getConfirmPassword().equals(staffForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.staffForm.confirmPassword");
			}
		}

	}

}
