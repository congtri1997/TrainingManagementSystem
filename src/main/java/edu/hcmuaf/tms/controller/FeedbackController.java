package edu.hcmuaf.tms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.Feedback;
import edu.hcmuaf.tms.form.FeedbackDTO;
import edu.hcmuaf.tms.service.impl.FeedbackService;
import edu.hcmuaf.tms.service.impl.FeedbackTypeService;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackTypeService feedbackTypeService;

	@RequestMapping(value = { "/trainee/feedback" }, method = RequestMethod.GET)
	public String listTrainee(ModelMap model) {
		model.addAttribute("feedbackTypes", feedbackTypeService.findAll());
		return "trainee/feedback";
	}

	@RequestMapping(value = "/trainee/feedbacks/{id}", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<Enrollment> findTrainee(@Valid DataTablesInput input,
			@PathVariable(name = "id") long traineeID) {
		return feedbackService.findAll(input, traineeID);
	}

	@RequestMapping(value = { "/trainee/feedback" }, method = RequestMethod.POST)
	public @ResponseBody String doFeedback(ModelMap model, @RequestBody FeedbackDTO feedbackDTO) {
		System.out.println(feedbackDTO);
		feedbackService.doFeedback(feedbackDTO, 9l);
		return "abc";
	}

	@RequestMapping(value = { "/trainee/getListFeedback" }, method = RequestMethod.GET)
	public @ResponseBody List<Feedback> getListFeedback(ModelMap model, @RequestParam("traineeID") long traineeID,
			@RequestParam("topicID") long topicID) {
		
		return feedbackService.getListFeedback(topicID, traineeID);
	}
}
