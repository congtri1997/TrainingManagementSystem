package edu.hcmuaf.tms.controller;

import java.security.Principal;
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

import edu.hcmuaf.tms.entity.AbstractUser;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.service.impl.AbstractUserService;
import edu.hcmuaf.tms.service.impl.TrainerService;
import edu.hcmuaf.tms.service.impl.WorkingTypeService;
import edu.hcmuaf.tms.validator.TrainerAddValidator;
import edu.hcmuaf.tms.validator.TrainerChangePasswordValidator;
import edu.hcmuaf.tms.validator.TrainerUpdateValidator;

@Controller
public class TrainerController {

	@Autowired
	private TrainerAddValidator trainerAddValidator;

	@Autowired
	private TrainerUpdateValidator trainerUpdateValidator;
	@Autowired
	private TrainerService trainerService;

	@Autowired
	private WorkingTypeService workingTypeService;
	@Autowired
	private TrainerChangePasswordValidator trainerChangePasswordValidator;

	@Autowired
	private ReloadableResourceBundleMessageSource message;

	@Autowired
	private AbstractUserService abstractUserService;

	@RequestMapping(value = { "/staff/trainer/list" }, method = RequestMethod.GET)
	public String listTrainer(ModelMap model) {
		model.addAttribute("trainers", trainerService.listAll());
		return "trainer/list";
	}

	@RequestMapping(value = { "/staff/trainer/add" }, method = RequestMethod.GET)
	public String addTrainer(ModelMap model) {
		model.addAttribute("trainerForm", new TrainerForm());
		model.addAttribute("workingTypes", workingTypeService.findAll());
		return "trainer/add";
	}

	@RequestMapping(value = { "/staff/trainer/add" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddTrainer(ModelMap model,
			@ModelAttribute("trainerForm") TrainerForm trainerForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		trainerAddValidator.validate(trainerForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			trainerService.addTrainer(trainerForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/trainer/update/{id}" }, method = RequestMethod.GET)
	public String editTrainer(ModelMap model, @PathVariable("id") long id) {
		Trainer trainer = trainerService.getOne(id);
		if (trainer == null)
			return "redirect:/staff/trainer/list";
		model.addAttribute("trainerForm", TrainerForm.convertToTrainerForm(trainer));
		model.addAttribute("workingTypes", workingTypeService.findAll());

		return "trainer/update";
	}

	@RequestMapping(value = { "/trainer/update" }, method = RequestMethod.GET)
	public String editTrainer(ModelMap model, Principal principal) {
		AbstractUser abstractUser = abstractUserService.getAbstractUser(principal.getName());
		Trainer trainer = trainerService.getOne(abstractUser.getId());
		if (trainer == null)
			return "redirect:/";
		model.addAttribute("trainerForm", TrainerForm.convertToTrainerForm(trainer));
		model.addAttribute("workingTypes", workingTypeService.findAll());
		return "trainer/trainer_update.html";
	}

	@RequestMapping(value = { "/staff/trainer/view/{id}" }, method = RequestMethod.GET)
	public String viewTrainer(ModelMap model, @PathVariable("id") long id) {
		Trainer trainer = trainerService.getOne(id);
		if (trainer == null)
			return "redirect:/staff/trainer/list";
		model.addAttribute("trainerForm", TrainerForm.convertToTrainerForm(trainer));
		return "trainer/view";
	}

	@RequestMapping(value = { "/staff/trainer/update", "/trainer/update" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doEditTrainer(ModelMap model,
			@ModelAttribute("trainerForm") TrainerForm trainerForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		trainerUpdateValidator.validate(trainerForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			trainerService.updateTrainer(trainerForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/trainer/changePassword","/trainer/changePassword" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doChangePass(ModelMap model,
			@ModelAttribute("trainerForm") TrainerForm trainerForm, BindingResult result) {
		JsonRespone jsonRespone = new JsonRespone();
		trainerChangePasswordValidator.validate(trainerForm, result);
		if (result.hasErrors()) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				hashMap.put(fieldError.getField(), message.getMessage(fieldError, Locale.getDefault()));
			}
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(hashMap);
		} else {
			jsonRespone.setValidated(true);
			trainerService.changePassword(trainerForm);
		}
		return jsonRespone;
	}

	@RequestMapping(value = { "/staff/trainer/delete/{id}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doAddTrainer(ModelMap model, @PathVariable("id") long id) {
		JsonRespone jsonRespone = new JsonRespone();
		trainerService.delete(id);
		jsonRespone.setValidated(true);
		jsonRespone.setMessage("Xóa thành công");
		return jsonRespone;
	}

	// datatable

	@RequestMapping(value = "/staff/trainer/trainers", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<TrainerForm> list(@Valid DataTablesInput input) {
		return trainerService.findAll(input);
	}

}
