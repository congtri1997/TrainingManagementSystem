package edu.hcmuaf.tms.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.service.impl.AbstractUserService;
import edu.hcmuaf.tms.service.impl.ViewCourseTopicThatAssignedToTrainerService;

@Controller
public class ViewCourseTopicThatAssignedToTrainerController {

	@Autowired
	private ViewCourseTopicThatAssignedToTrainerService viewCourseTopicThatAssignedToTrainerService;

	@Autowired
	private AbstractUserService abstractUserService;

	@RequestMapping(value = { "/trainer/view_course_topic_that_assigned_to" }, method = RequestMethod.GET)
	public String addTraineeIntoACourse(ModelMap model) {
		return "trainer/view_course_topic_assigned_to";
	}

	@RequestMapping(value = "/trainer/topic/trainer", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<TopicForm> findTrainee(@Valid DataTablesInput input, Principal principal) {
		Long trainerID = null;
		if (principal != null)
			trainerID = abstractUserService.getAbstractUser(principal.getName()).getId();
		return viewCourseTopicThatAssignedToTrainerService.findAll(input, trainerID);
	}

}
