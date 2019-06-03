package edu.hcmuaf.tms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.repository.TrainerDatatableRepository;

@RestController
public class TrainerDatatableController {
	
	@Autowired
	private TrainerDatatableRepository trainerDatatableRepository;

	@RequestMapping(value = "/staff/trainer/trainers", method = RequestMethod.GET)
	public DataTablesOutput<Trainer> list(@Valid DataTablesInput input) {
		return trainerDatatableRepository.findAll(input);
	}

	@RequestMapping(value = "/staff/trainer/trainers", method = RequestMethod.POST)
	public DataTablesOutput<Trainer> listPOST(@Valid @RequestBody DataTablesInput input) {
		return trainerDatatableRepository.findAll(input);
	}

}
