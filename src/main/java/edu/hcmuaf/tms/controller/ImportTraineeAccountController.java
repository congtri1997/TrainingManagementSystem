package edu.hcmuaf.tms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hcmuaf.tms.form.ImportTraineeAccountDTO;
import edu.hcmuaf.tms.form.JsonRespone;
import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.service.impl.TraineeService;

@Controller
public class ImportTraineeAccountController {

	@Autowired
	private TraineeService traineeService;

	@RequestMapping(value = { "/staff/import_trainee_account" }, method = RequestMethod.GET)
	public String importTraineeAccount(ModelMap model) {
		return "import_trainee_account";
	}

	@RequestMapping(value = { "/staff/import_trainee_account" }, method = RequestMethod.POST)
	public @ResponseBody JsonRespone doImportTraineeAccount(ModelMap model,
			@RequestBody ImportTraineeAccountDTO importTraineeAccounts) {
		JsonRespone jsonRespone = new JsonRespone();
		boolean isError = false;
		Map<String, String> errors = new HashMap<String, String>();
//		System.out.println(importTraineeAccounts.getTraineeForms());
		for (TraineeForm traineeForm : importTraineeAccounts.getTraineeForms()) {
//			System.out.println(traineeForm.getNumber() +" - "+ traineeForm.getUserName()+ " - " + traineeForm.getPassword());
			if (traineeForm.getUserName() == null || traineeForm.getUserName().trim().isEmpty() || traineeForm.getPassword() == null
					|| traineeForm.getPassword().trim().isEmpty() || traineeService.isUsernameAlreadyExist(traineeForm.getUserName())) {
				isError = true;
				errors.put(traineeForm.getNumber()+"", "Tên tài khoản và mật khẩu không được để trống hoặc có thể tài khoản đã được sử dụng");
			
			}

		}
		if (isError) {
			jsonRespone.setValidated(false);
			jsonRespone.setErrorMessages(errors);
		} else {
			jsonRespone.setValidated(true);
			traineeService.importAccount(importTraineeAccounts);
		}

		return jsonRespone;
	}
}
