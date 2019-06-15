package edu.hcmuaf.tms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.service.impl.ViewCourseTopicThatAssignedToTrainerService;

@Controller
public class ViewCourseTopicThatAssignedToTrainerController {
	
	@Autowired
	private ViewCourseTopicThatAssignedToTrainerService viewCourseTopicThatAssignedToTrainerService;
	
	@RequestMapping(value = { "/staff/view_course_topic_that_assigned_to" }, method = RequestMethod.GET)
	public String addTraineeIntoACourse(ModelMap model) {
		return "trainer/view_course_topic_assigned_to";
	}
	
	@RequestMapping(value = "/staff/topic/trainer/{id}", method = RequestMethod.GET)
	public @ResponseBody DataTablesOutput<TopicForm> findTrainee(@Valid DataTablesInput input,@PathVariable(name = "id") long trainerID) {
		return viewCourseTopicThatAssignedToTrainerService.findAll(input, trainerID);
	}
	
}
