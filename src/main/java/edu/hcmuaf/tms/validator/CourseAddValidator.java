package edu.hcmuaf.tms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.CourseForm;
import edu.hcmuaf.tms.service.impl.CourseService;

@Component
public class CourseAddValidator implements Validator {

	@Autowired
	private CourseService courseService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CourseForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		CourseForm courseForm = (CourseForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.courseForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.courseForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.courseForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.courseForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.courseForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "NotEmpty.courseForm.birthDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scoreOfToeic", "NotEmpty.courseForm.scoreOfToeic");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "detailsOfExp", "NotEmpty.courseForm.detailsOfExp");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "NotEmpty.courseForm.department");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.courseForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education", "NotEmpty.courseForm.education");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "programmingLanguage", "NotEmpty.courseForm.programmingLanguage");
	}

}
