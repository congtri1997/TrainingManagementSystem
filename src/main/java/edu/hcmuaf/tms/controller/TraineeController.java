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

import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.service.impl.ProgrammingLanguageService;
import edu.hcmuaf.tms.service.impl.TraineeService;
import edu.hcmuaf.tms.validator.TraineeAddValidator;
import edu.hcmuaf.tms.validator.TraineeChangePasswordValidator;
import edu.hcmuaf.tms.validator.TraineeUpdateValidator;

@Controller
public class TraineeController {

	@Autowired
	private TraineeAddValidator traineeAddValidator;

	@Autowired
	private TraineeUpdateValidator traineeUpdateValidator;
	@Autowired
	private TraineeService traineeService;

	@Autowired
	private TraineeChangePasswordValidator traineeChangePasswordValidator;

	@Autowired
	private ReloadableResourceBundleMessageSource message;

	@Autowired
	private ProgrammingLanguageService programmingLanguageService;

	@RequestMapping(value = { "/staff/trainee/list" }, method = RequestMethod.GET)
	public String listTrainee(ModelMap model) {
		model.addAttribute("trainees", traineeService.listAll());
		model.addAttribute("programmingLanguages", programmingLanguageService.findAll());
		return "trainee/trainee_list";
	}

	@RequestMapping(value = { "/staff/trainee/add" }, method = RequestMethod.GET)
	public String addTrainee(ModelMap model) {
		model.addAttribute("traineeForm", new TraineeForm());
		model.addAttribute("programmingLanguages", programmingLanguageService.findAll());
		return "trainee/trainee_add";
	}

	@RequestMapping(value = { "/staff/trainee/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddTrainee(ModelMap model,
			@ModelAttribute("traineeForm") TraineeForm traineeForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		traineeAddValidator.validate(traineeForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			traineeService.addTrainee(traineeForm);
		}
		return jsonRespone;
	}

	/*
	 * @RequestMapping(value = { "/staff/trainee/add" }, method =
	 * RequestMethod.POST) public String doAddTrainee(ModelMap
	 * model, @ModelAttribute("traineeForm") TraineeForm traineeForm, BindingResult
	 * result) { traineeAddValidator.validate(traineeForm, result); if
	 * (result.hasErrors()) { model.addAttribute("workingTypes",
	 * workingTypeService.findAll()); return "trainee/trainee_add"; }
	 * 
	 * traineeService.addTrainee(traineeForm); return "redirect:/staff/trainee/add";
	 * }
	 */
	@RequestMapping(value = { "/staff/trainee/update/{id}" }, method = RequestMethod.GET)
	public String editTrainee(ModelMap model, @PathVariable("id") long id) {
		Trainee trainee = traineeService.getOne(id);
		if (trainee == null)
			return "redirect:/staff/trainee/list";
		model.addAttribute("traineeForm", TraineeForm.toDTO(trainee));
		model.addAttribute("programmingLanguages", programmingLanguageService.findAll());
		return "trainee/trainee_update";
	}

	@RequestMapping(value = { "/staff/trainee/view/{id}" }, method = RequestMethod.GET)
	public String viewTrainee(ModelMap model, @PathVariable("id") long id) {
		Trainee trainee = traineeService.getOne(id);
		if (trainee == null)
			return "redirect:/staff/trainee/list";
		model.addAttribute("traineeForm", TraineeForm.toDTO(trainee));
		return "trainee/trainee_view";
	}

	@RequestMapping(value = { "/staff/trainee/update" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doEditTrainee(ModelMap model,
			@ModelAttribute("traineeForm") TraineeForm traineeForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		traineeUpdateValidator.validate(traineeForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			traineeService.updateTrainee(traineeForm);
		}
		return jsonRespone;
	}
//	@RequestMapping(value = { "/staff/trainee/update" }, method = RequestMethod.POST)
//	public String doEditTrainee(ModelMap model, @ModelAttribute("traineeForm") TraineeForm traineeForm,
//			BindingResult result) {
//		traineeUpdateValidator.validate(traineeForm, result);
//		if (result.hasErrors()) {
//			return "trainee/trainee_update";
//		}
//		traineeService.updateTrainee(traineeForm);
//		return "redirect:/staff/trainee/list";
//	}

	@RequestMapping(value = { "/staff/trainee/changePassword" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doChangePass(ModelMap model,
			@ModelAttribute("traineeForm") TraineeForm traineeForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		traineeChangePasswordValidator.validate(traineeForm, result);

		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			traineeService.changePassword(traineeForm);
		}
		return jsonRespone;
	}
//	@RequestMapping(value = { "/staff/trainee/changePassword" }, method = RequestMethod.POST)
//	public String doChangePass(ModelMap model, @ModelAttribute("traineeForm") TraineeForm traineeForm,
//			BindingResult result) {
//		TraineeChangePasswordValidator.validate(traineeForm, result);
//		if (result.hasErrors()) {
//			return "trainee/trainee_update";
//		}
//		traineeService.changePassword(traineeForm);
//		return "redirect:/staff/trainee/list";
//	}

	@RequestMapping(value = { "/staff/trainee/delete/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doAddTrainee(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		traineeService.delete(id);
		jsonRespone.setValidated(true);
		jsonRespone.setMessage("Xóa thành công");
		return jsonRespone;
	}

	// Data table section
	@RequestMapping(value = "/staff/trainee/trainees", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<TraineeForm> list(@Valid DataTablesInput input) {
		return traineeService.findAll(input);
	}

	// end data table section

}
