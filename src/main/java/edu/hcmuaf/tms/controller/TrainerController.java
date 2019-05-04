package edu.hcmuaf.tms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.TrainerForm;
import edu.hcmuaf.tms.service.impl.TrainerService;
import edu.hcmuaf.tms.validator.TrainerValidator;

@Controller
public class TrainerController {

	@Autowired
	private TrainerValidator trainerValidator;
	@Autowired
	private TrainerService trainerService;

	/*
	 * // Set a form validator
	 * 
	 * @InitBinder protected void initBinder(WebDataBinder dataBinder) { // Form mục
	 * tiêu Object target = dataBinder.getTarget(); if (target == null) { return; }
	 * System.out.println("Target=" + target);
	 * 
	 * if (target.getClass() == TrainerForm.class) {
	 * dataBinder.setValidator(trainerValidator); } // ... }
	 */

	@RequestMapping(value = { "/staff/trainer/list" }, method = RequestMethod.GET)
	public String listTrainer(ModelMap model) {
		model.addAttribute("trainers", trainerService.listAll());
		return "trainer/list-trainer";
	}

	@RequestMapping(value = { "/staff/trainer/add" }, method = RequestMethod.GET)
	public String addTrainer(ModelMap model) {
		model.addAttribute("trainerForm", new TrainerForm());

		return "trainer/add-trainer";
	}

	@RequestMapping(value = { "/staff/trainer/view/{id}" }, method = RequestMethod.GET)
	public String viewDetail(ModelMap model, @PathVariable("id") long id) {
		Trainer trainer = trainerService.getOne(id);

		model.addAttribute("trainerForm", new TrainerForm());
		model.addAttribute("trainer", trainer);
		return "trainer/viewdetail-trainer";
	}

	@RequestMapping(value = { "/staff/trainer/add" }, method = RequestMethod.POST)
	public String doAddTrainer(ModelMap model, @ModelAttribute("trainerForm") TrainerForm trainerForm,
			BindingResult result) {
		trainerValidator.validate(trainerForm, result);
		if (result.hasErrors()) {
			return "trainer/add-trainer";
		}

		trainerService.addTrainer(trainerForm);

		return "redirect:/staff/trainer/list";
	}

	@RequestMapping(value = { "/staff/trainer/edit/{id}" }, method = RequestMethod.GET)
	public String editTrainer(ModelMap model, @PathVariable("id") long id) {
		Trainer trainer = trainerService.getOne(id);
		model.addAttribute("trainerForm", new TrainerForm(trainer));

		return "trainer/edit-trainer";
	}

	@RequestMapping(value = { "/staff/trainer/edit" }, method = RequestMethod.POST)
	public String doEditTrainer(ModelMap model, @ModelAttribute("trainerForm") TrainerForm trainerForm,
			BindingResult result) {
		trainerValidator.validate(trainerForm, result);
		if (result.hasErrors()) {
			return "trainer/edit-trainer";
		}
		trainerService.updateTrainer(trainerForm);
		return "redirect:/staff/trainer/list";
	}

}
