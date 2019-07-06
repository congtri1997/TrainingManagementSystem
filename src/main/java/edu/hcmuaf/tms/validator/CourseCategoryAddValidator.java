package edu.hcmuaf.tms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.CourseCategoryForm;

@Component
public class CourseCategoryAddValidator implements Validator {

	/*
	 * @Autowired private CourseService courseService;
	 */

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CourseCategoryForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

//		CourseForm courseForm = (CourseForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.courseCategoryForm.name");
		
		
	}

}
