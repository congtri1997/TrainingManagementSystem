package edu.hcmuaf.tms.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.repository.TopicRepository;

@Service
@Transactional
public class AddTrainerIntoTopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public DataTablesOutput<TopicForm> findAll(@Valid DataTablesInput input) {
		return topicRepository.findAll(input, null, null, (Topic t) -> TopicForm.toDTO(t));
	}
}
