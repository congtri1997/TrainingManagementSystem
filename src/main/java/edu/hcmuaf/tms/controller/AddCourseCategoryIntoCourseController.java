package edu.hcmuaf.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.form.AddCourseCategoryIntoACourseDTO;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.service.impl.AddCourseCategoryIntoCourseService;
import edu.hcmuaf.tms.service.impl.CourseCategoryService;

@Controller
public class AddCourseCategoryIntoCourseController {

	@Autowired
	private AddCourseCategoryIntoCourseService addCourseCategoryIntoCourseService;
	@Autowired
	private CourseCategoryService courseCategoryService;

	@RequestMapping(value = { "/staff/add_course_category_into_course" }, method = RequestMethod.GET)
	public String addTraineeIntoACourse(ModelMap model) {
		model.addAttribute("addCourseCategoryIntoCourseDTO", new AddCourseCategoryIntoACourseDTO());
		model.addAttribute("courseCategories", courseCategoryService.listAll());
		return "add_course_category_into_course";
	}

//	@RequestMapping(value = { "/staff/rest/find_trainee_not_in_a_course/{id}" }, method = RequestMethod.GET)
//	public @ResponseBody List<Trainee> findTraineeNotInACourse(ModelMap model, @PathVariable(name = "id") long id) {
//		return traineeService.findTraineeNotInACourse(id);
//	}
//
//	@RequestMapping(value = { "/staff/rest/find_trainee_in_a_course/{id}" }, method = RequestMethod.GET)
//	public @ResponseBody List<Trainee> findTraineeInACourse(ModelMap model, @PathVariable(name = "id") long id) {
//		return traineeService.findTraineeInACourse(id);
//	}

	@RequestMapping(value = { "/staff/add_course_category_into_course" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddTraineeIntoACourse(ModelMap model,
			@ModelAttribute("addCourseCategoryIntoCourseDTO") AddCourseCategoryIntoACourseDTO addCourseCategoryIntoCourseDTO) {
		JsonRespone jsonRespone = new JsonRespone();

		addCourseCategoryIntoCourseService.addCourseCategoryIntoCourse(addCourseCategoryIntoCourseDTO);
		return jsonRespone;
	}
	
	
	@RequestMapping(value = { "/staff/rest/find_course_category_in_a_course/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<CourseCategory> findCourseCategoryInACourse(ModelMap model, @PathVariable(name = "id") long id) {
		return addCourseCategoryIntoCourseService.getListCourseCategory(id);
	}

}
