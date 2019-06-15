package edu.hcmuaf.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.AddTraineeIntoACourseDTO;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.service.impl.AddTraineeIntoACourseService;
import edu.hcmuaf.tms.service.impl.EnrollmentService;
import edu.hcmuaf.tms.service.impl.TraineeService;

@Controller
public class AddTraineeIntoACourseController {

	@Autowired
	private TraineeService traineeService;

	@Autowired
	private AddTraineeIntoACourseService addTraineeIntoACourseService;

	@Autowired
	private EnrollmentService enrollmentService;

	@RequestMapping(value = { "/staff/add_trainee_into_a_course" }, method = RequestMethod.GET)
	public String addTraineeIntoACourse(ModelMap model) {
		model.addAttribute("addTraineeIntoACourseDTO", new AddTraineeIntoACourseDTO());
		return "add_trainee_into_a_course";
	}

	@RequestMapping(value = { "/staff/rest/find_trainee_not_in_a_course/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<Trainee> findTraineeNotInACourse(ModelMap model, @PathVariable(name = "id") long id) {
		return traineeService.findTraineeNotInACourse(id);
	}

	@RequestMapping(value = { "/staff/rest/find_trainee_in_a_course/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<Trainee> findTraineeInACourse(ModelMap model, @PathVariable(name = "id") long id) {
		return traineeService.findTraineeInACourse(id);
	}

	@RequestMapping(value = { "/staff/add_trainee_into_a_course" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doAddTraineeIntoACourse(ModelMap model,
			@ModelAttribute("addTraineeIntoACourseDTO") AddTraineeIntoACourseDTO addTraineeIntoACourseDTO) {
		JsonRespone jsonRespone = new JsonRespone();

		addTraineeIntoACourseService.addTraineeIntoACourse(addTraineeIntoACourseDTO);
		return jsonRespone;
	}

	@RequestMapping(value = {
			"/staff/rest/delete_trainee_from_a_course/{courseID}/{traineeID}" }, method = RequestMethod.DELETE)
	public @ResponseBody JsonRespone doDeleteTopic(ModelMap model, @PathVariable("courseID") long courseID,
			@PathVariable("traineeID") long traineeID) {
		JsonRespone jsonRespone = new JsonRespone();
		enrollmentService.removeTraineeFromCourse(courseID, traineeID);
		jsonRespone.setValidated(true);
		jsonRespone.setMessage("Xóa thành công");
		return jsonRespone;
	}

}
