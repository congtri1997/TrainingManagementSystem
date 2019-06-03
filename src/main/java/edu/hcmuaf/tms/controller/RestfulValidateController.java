package edu.hcmuaf.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hcmuaf.tms.service.impl.AbstractUserService;
import edu.hcmuaf.tms.service.impl.TrainerService;

@RestController
public class RestfulValidateController {

	@Autowired
	private AbstractUserService abstractUserService;

	@Autowired
	private TrainerService trainerService;

	@RequestMapping(value = "/restful/trainer/validateEmail", method = RequestMethod.GET, produces = "application/json")
	public String validateEmailExist(@RequestParam(value = "email") String email) {
		if (trainerService.isEmailAlreadyExist(email))
			return "false";
		return "true";
	}

	@RequestMapping(value = "/restful/validateUserName", method = RequestMethod.GET, produces = "application/json")
	public String validateUserNameExist(@RequestParam(value = "userName") String userName) {
		if (abstractUserService.isUserNameAlreadyExisted(userName))
			return "false";
		return "true";
	}

}
