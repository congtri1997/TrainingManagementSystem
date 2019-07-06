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
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.form.CourseCategoryForm;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.service.impl.CourseCategoryService;
import edu.hcmuaf.tms.validator.CourseCategoryAddValidator;
import edu.hcmuaf.tms.validator.CourseCategoryUpdateValidator;

@Controller
public class CourseCategoryController {

	@Autowired
	private CourseCategoryService courseCategoryService;

	@Autowired
	private CourseCategoryAddValidator courseCategoryAddValidator;

	@Autowired
	private CourseCategoryUpdateValidator courseCategoryUpdateValidator;

	@Autowired
	private ReloadableResourceBundleMessageSource message;
	

	@RequestMapping(value = { "/staff/course_category/list" }, method = RequestMethod.GET)
	public String listCourseCategory(ModelMap model) {
		return "course_category/list";
	}

	@RequestMapping(value = { "/staff/course_category/add" }, method = RequestMethod.GET)
	public String addCourseCategory(ModelMap model) {
		model.addAttribute("courseCategoryForm", new CourseCategoryForm());
		return "course_category/add";
	}

	@RequestMapping(value = { "/staff/course_category/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddCourseCategory(ModelMap model, @ModelAttribute("courseCategoryForm") CourseCategoryForm courseCategoryForm,
			BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		courseCategoryAddValidator.validate(courseCategoryForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			courseCategoryService.addCourseCategory(courseCategoryForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/course_category/update/{id}" }, method = RequestMethod.GET)
	public String editCourseCategory(ModelMap model, @PathVariable("id") long id) {
		CourseCategory courseCategory = courseCategoryService.getOne(id);
		if (courseCategory == null)
			return "redirect:/staff/course_category/list";
		model.addAttribute("courseCategoryForm", CourseCategoryForm.toDTO(courseCategory));
		return "course_category/update";
	}

	@RequestMapping(value = { "/staff/course_category/update" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doEditCourseCategory(ModelMap model, @ModelAttribute("courseCategoryForm") CourseCategoryForm courseCategoryForm,
			BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		courseCategoryUpdateValidator.validate(courseCategoryForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			courseCategoryService.updateCourseCategory(courseCategoryForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/course_category/view/{id}" }, method = RequestMethod.GET)
	public String viewCourseCategory(ModelMap model, @PathVariable("id") long id) {
		CourseCategory courseCategory = courseCategoryService.getOne(id);
		if (courseCategory == null)
			return "redirect:/staff/course_category/list";
		model.addAttribute("courseCategoryForm", CourseCategoryForm.toDTO(courseCategory));
		return "course_category/view";
	}

	@RequestMapping(value = { "/staff/course_category/delete/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doDeleteCourse(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		try {
			courseCategoryService.delete(id);
			
			jsonRespone.setValidated(true);
			jsonRespone.setMessage("Xóa thành công");
		} catch(Exception e) {
			jsonRespone.setValidated(false);
			jsonRespone.setMessage("Dữ liệu đang bị ràng buộc");
		}
		return jsonRespone;
	}

	// Data table section
	@RequestMapping(value = "/staff/course_category/course_categories", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<CourseCategoryForm> list(@Valid DataTablesInput input) {
		return courseCategoryService.findAll(input);
	}

	// end data table section



}
