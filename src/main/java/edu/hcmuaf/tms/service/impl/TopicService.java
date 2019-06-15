package edu.hcmuaf.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.repository.TopicRepository;
import edu.hcmuaf.tms.repository.TrainerRepository;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	public void delete(long id) {
		if (topicRepository.existsById(id)) {
			topicRepository.delete(topicRepository.getOne(id));
		}
	}

	public void addTrainerIntoTopicService(TopicForm topicForm) {
		if (topicForm != null)
			if (topicForm.getId() != null && topicRepository.existsById(topicForm.getId())) {
				Topic topic = topicRepository.getOne(topicForm.getId());
				Trainer trainer = null;
		 		if (topicForm.getTrainer() != null && trainerRepository.existsById(topicForm.getTrainer().getId())) {
					trainer = trainerRepository.getOne(topicForm.getTrainer().getId());
				}

				topic.setTrainer(trainer);

				topicRepository.save(topic);
			}
	}

}
