package edu.hcmuaf.tms.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.AbstractUser;
import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.Feedback;
import edu.hcmuaf.tms.form.FeedbackDTO;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.service.impl.AbstractUserService;
import edu.hcmuaf.tms.service.impl.FeedbackService;
import edu.hcmuaf.tms.service.impl.FeedbackTypeService;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackTypeService feedbackTypeService;

	@Autowired
	private AbstractUserService abstractUserService;

	@RequestMapping(value = { "/trainee/feedback" }, method = RequestMethod.GET)
	public String listTrainee(ModelMap model) {
		model.addAttribute("feedbackTypes", feedbackTypeService.findAll());
		return "trainee/feedback";
	}

	@RequestMapping(value = "/trainee/feedbacks", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<Enrollment> findTrainee(@Valid DataTablesInput input, Principal principal) {
		AbstractUser abstractUser = abstractUserService.getAbstractUser(principal.getName());
		return feedbackService.findAll(input, abstractUser.getId());
	}

	@RequestMapping(value = { "/trainee/feedback" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doFeedback(ModelMap model, @RequestBody FeedbackDTO feedbackDTO,
			Principal principal) {
		JsonRespone jsonRespone = new JsonRespone();
		AbstractUser abstractUser = abstractUserService.getAbstractUser(principal.getName());
		feedbackService.doFeedback(feedbackDTO, abstractUser.getId());
		return jsonRespone;
	}

	@RequestMapping(value = { "/trainee/getListFeedback" }, method = RequestMethod.GET)
	public @ResponseBody List<Feedback> getListFeedback(ModelMap model, @RequestParam("topicID") long topicID,
			Principal principal) {
		AbstractUser abstractUser = abstractUserService.getAbstractUser(principal.getName());
		return feedbackService.getListFeedback(topicID, abstractUser.getId());
	}
}
