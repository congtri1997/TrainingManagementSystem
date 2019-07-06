package edu.hcmuaf.tms.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.form.CourseForm;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.service.impl.CourseService;
import edu.hcmuaf.tms.service.impl.TopicService;
import edu.hcmuaf.tms.validator.AddTopicIntoACourseValidator;
import edu.hcmuaf.tms.validator.CourseAddValidator;
import edu.hcmuaf.tms.validator.CourseUpdateValidator;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseAddValidator courseAddValidator;

	@Autowired
	private CourseUpdateValidator courseUpdateValidator;
	@Autowired
	private AddTopicIntoACourseValidator addTopicIntoACourseValidator;

	@Autowired
	private ReloadableResourceBundleMessageSource message;
	
	@Autowired
	private TopicService topicservice;

	@RequestMapping(value = { "/staff/course/list" }, method = RequestMethod.GET)
	public String listTrainee(ModelMap model) {
		return "course/list";
	}

	@RequestMapping(value = { "/staff/course/add" }, method = RequestMethod.GET)
	public String addTrainer(ModelMap model) {
		model.addAttribute("courseForm", new CourseForm());
		return "course/add";
	}

	@RequestMapping(value = { "/staff/course/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddCourse(ModelMap model, @ModelAttribute("courseForm") CourseForm courseForm,
			BindingResult result) {
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

	@RequestMapping(value = { "/staff/course/update/{id}" }, method = RequestMethod.GET)
	public String editCourse(ModelMap model, @PathVariable("id") long id) {
		Course course = courseService.getOne(id);
		if (course == null)
			return "redirect:/staff/course/list";
		model.addAttribute("courseForm", CourseForm.toDTO(course));
		return "course/update";
	}

	@RequestMapping(value = { "/staff/course/update" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doEditCourse(ModelMap model, @ModelAttribute("courseForm") CourseForm courseForm,
			BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		courseUpdateValidator.validate(courseForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			courseService.updateCourse(courseForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/course/view/{id}" }, method = RequestMethod.GET)
	public String viewCourse(ModelMap model, @PathVariable("id") long id) {
		Course course = courseService.getOne(id);
		if (course == null)
			return "redirect:/staff/course/list";
		model.addAttribute("courseForm", CourseForm.toDTO(course));
		return "course/view";
	}

	@RequestMapping(value = { "/staff/course/delete/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doDeleteCourse(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		try {
			courseService.delete(id);
			jsonRespone.setValidated(true);
			jsonRespone.setMessage("Xóa thành công");
			
		} catch(Exception e) {
			jsonRespone.setValidated(false);
			jsonRespone.setMessage("Dữ liệu đang bị ràng buộc");
			
		}
		return jsonRespone;
	}

	// Data table section
	@RequestMapping(value = "/staff/course/courses", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<CourseForm> list(@Valid DataTablesInput input) {
		return courseService.findAll(input);
	}

	// end data table section

	@RequestMapping(value = { "/staff/add_topic_into_a_course" }, method = RequestMethod.GET)
	public String viewCourse(ModelMap model) {

		model.addAttribute("topicForm", new TopicForm());
		model.addAttribute("courses", courseService.listAll());
		return "add_topic_into_a_course";
	}

	@RequestMapping(value = "/staff/getCourse", method = RequestMethod.GET)
	public @ResponseBody CourseForm list(@RequestParam(name = "courseID", required = false) Long courseID) {
		return CourseForm.toDTOWithTopic(courseService.getOne(courseID));
	}
	
	@RequestMapping(value = { "/staff/add_topic_into_a_course" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone addTopicIntoACourse(ModelMap model, @ModelAttribute("topicForm") TopicForm topicForm,
			BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		
		System.out.println(topicForm);
		addTopicIntoACourseValidator.validate(topicForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			courseService.addTopicIntoACourse(topicForm);
		}
		return jsonRespone;
	}
	
	
	@RequestMapping(value = { "/staff/course/deleteTopic/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doDeleteTopic(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		try {
			topicservice.delete(id);
			jsonRespone.setValidated(true);
			jsonRespone.setMessage("Xóa thành công");
			
		} catch(Exception e) {
			jsonRespone.setValidated(false);
			jsonRespone.setMessage("Dữ liệu đang bị ràng buộc");
			
		}
		return jsonRespone;
	}


}
