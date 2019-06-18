package edu.hcmuaf.tms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.ChartTrainer;
import edu.hcmuaf.tms.service.impl.TopicService;

@Controller
public class TopTrainerWhoTeachMostController {

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = { "/staff/rest/top_trainer_who_teach_most" }, method = { RequestMethod.GET })
	public @ResponseBody List<ChartTrainer> topTrainerWhoTeachMost(@RequestParam("n") int n,
			@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate start = null;
		LocalDate end = null;
		try {
			start = LocalDate.parse(startDate, formatter);
			end = LocalDate.parse(endDate, formatter);
		} catch (DateTimeParseException e) {
		}
		
		return topicService.getTopTrainerWhoTeachTheMost(n, start, end);
	}
	
	
	@RequestMapping(value = { "/staff/top_trainer_who_teach_most" }, method = { RequestMethod.GET })
	public String chart() {
		return "top_trainer_who_teach_most";
	}

}
