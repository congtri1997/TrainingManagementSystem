package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.CourseCategoryForm;
import edu.hcmuaf.tms.service.impl.CourseCategoryService;

@Component
public class CourseCategoryUpdateValidator implements Validator {

	@Autowired
	private CourseCategoryService courseCategoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CourseCategoryForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		CourseCategoryForm courseCategoryForm = (CourseCategoryForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.courseCategoryForm.firstName");

		if (courseCategoryForm.getId() == null || !courseCategoryService.existsById(courseCategoryForm.getId())) {
			errors.rejectValue("id", "NotExist.courseCategoryForm.id");
		}
	}

}
