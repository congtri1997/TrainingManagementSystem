package edu.hcmuaf.tms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.service.impl.AddTrainerIntoTopicService;
import edu.hcmuaf.tms.service.impl.TopicService;
import edu.hcmuaf.tms.service.impl.TrainerService;

@Controller
public class AddTrainerIntoTopicController {

	@Autowired
	private AddTrainerIntoTopicService addTrainerIntoTopicService;

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/staff/topic/topics", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<TopicForm> list(@Valid DataTablesInput input) {
		return addTrainerIntoTopicService.findAll(input);
	}

	@RequestMapping(value = { "/staff/add_trainer_into_a_topic" }, method = RequestMethod.GET)
	public String addTrainerIntoATopic(ModelMap model) {
		model.addAttribute("topicForm", new TopicForm());
		model.addAttribute("trainers", trainerService.listAll());
		return "add_trainer_into_a_topic";
	}

	@RequestMapping(value = { "/staff/add_trainer_into_a_topic" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAaddTrainerIntoATopic(ModelMap model,
			@ModelAttribute("topicForm") TopicForm topicForm, BindingResult result) {
		System.out.println("AAAAAA");
		JsonRespone jsonRespone = new JsonRespone();
		System.out.println(topicForm);
		System.out.println(topicForm.getTrainer().getId());
		topicService.addTrainerIntoTopicService(topicForm);
		return jsonRespone;
	}

}
