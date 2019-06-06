package edu.hcmuaf.tms.controller;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.CourseForm;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.service.impl.CourseService;
import edu.hcmuaf.tms.validator.CourseAddValidator;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseAddValidator courseAddValidator;
	

	@Autowired
	private ReloadableResourceBundleMessageSource message;

	@RequestMapping(value = { "/staff/course/add" }, method = RequestMethod.GET)
	public String addTrainer(ModelMap model) {
		model.addAttribute("courseForm", new CourseForm());
		return "course/course_add";
	}
	
	
	@RequestMapping(value = { "/staff/trainee/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddTrainee(ModelMap model,
			@ModelAttribute("courseForm") CourseForm courseForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		courseAddValidator.validate(courseForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			courseService.addCourse(courseForm);
		}
		return jsonRespone;
	}


}
