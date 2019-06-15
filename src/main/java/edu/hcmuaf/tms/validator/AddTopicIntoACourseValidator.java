package edu.hcmuaf.tms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.hcmuaf.tms.form.TopicForm;

@Component
public class AddTopicIntoACourseValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == TopicForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

//		CourseForm courseForm = (CourseForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.topicForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "NotEmpty.topicForm.startDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "NotEmpty.topicForm.duration");

	}
}
