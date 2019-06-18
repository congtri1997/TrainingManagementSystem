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

import edu.hcmuaf.tms.entity.Staff;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.StaffForm;
import edu.hcmuaf.tms.service.impl.StaffService;
import edu.hcmuaf.tms.validator.StaffAddValidator;
import edu.hcmuaf.tms.validator.StaffChangePasswordValidator;

@Controller
public class StaffController {

	@Autowired
	private StaffAddValidator staffAddValidator;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StaffChangePasswordValidator staffChangePasswordValidator;

	@Autowired
	private ReloadableResourceBundleMessageSource message;


	@RequestMapping(value = { "/admin/staff/list" }, method = RequestMethod.GET)
	public String listStaff(ModelMap model) {
		return "staff/list";
	}

	@RequestMapping(value = { "/admin/staff/add" }, method = RequestMethod.GET)
	public String addStaff(ModelMap model) {
		model.addAttribute("staffForm", new StaffForm());
		return "staff/add";
	}

	@RequestMapping(value = { "/admin/staff/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddStaff(ModelMap model,
			@ModelAttribute("staffForm") StaffForm staffForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		staffAddValidator.validate(staffForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			staffService.addStaff(staffForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/admin/staff/update/{id}" }, method = RequestMethod.GET)
	public String editStaff(ModelMap model, @PathVariable("id") long id) {
		Staff staff = staffService.getOne(id);
		if (staff == null)
			return "redirect:/staff/staff/list";
		model.addAttribute("staffForm", StaffForm.toDTO(staff));
		return "staff/update";
	}

	@RequestMapping(value = { "/admin/staff/view/{id}" }, method = RequestMethod.GET)
	public String viewStaff(ModelMap model, @PathVariable("id") long id) {
		Staff staff = staffService.getOne(id);
		if (staff == null)
			return "redirect:/staff/staff/list";
		model.addAttribute("staffForm", StaffForm.toDTO(staff));
		return "staff/view";
	}


	@RequestMapping(value = { "/admin/staff/changePassword" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doChangePass(ModelMap model,
			@ModelAttribute("staffForm") StaffForm staffForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		staffChangePasswordValidator.validate(staffForm, result);

		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			staffService.changePassword(staffForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/admin/staff/delete/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doAddStaff(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		staffService.delete(id);
		jsonRespone.setValidated(true);
		jsonRespone.setMessage("Xóa thành công");
		return jsonRespone;
	}

	// Data table section
	
	@RequestMapping(value = "/admin/staff/staffs", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<StaffForm> findStaff(@Valid DataTablesInput input) {
		return staffService.findAll(input);
	}

	// end data table section

}
