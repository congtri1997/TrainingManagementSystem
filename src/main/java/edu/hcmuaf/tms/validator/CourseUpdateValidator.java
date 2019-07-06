package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.CourseForm;
import edu.hcmuaf.tms.service.impl.CourseService;

@Component
public class CourseUpdateValidator implements Validator {

	@Autowired
	private CourseService courseService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CourseForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		CourseForm courseForm = (CourseForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.courseForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "NotEmpty.courseForm.startDate");
		
		if (courseForm.getId() == null ||!courseService.existsById(courseForm.getId())) {
			errors.rejectValue("id", "NotExist.courseForm.id");
		}
	}

}
