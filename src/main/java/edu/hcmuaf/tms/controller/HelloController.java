package edu.hcmuaf.tms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hcmuaf.tms.service.impl.TopicService;

@Controller
public class HelloController {
	
	@Autowired
	TopicService topicService; 

	@RequestMapping(value = { "/", "index.html" }, method = { RequestMethod.GET })
	public String index() {
		return "index";
	}
	



	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String loin() {
		return "login";
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
//
//		if (principal != null) {
//			User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
////			String userInfo = WebUtils.toString(loginedUser);
//
////			model.addAttribute("userInfo", userInfo);
//
//			String message = "Hi " + principal.getName() //
//					+ "<br> You do not have permission to access this page!";
//			model.addAttribute("message", message);

//		}

		return "error-403";
	}

}
