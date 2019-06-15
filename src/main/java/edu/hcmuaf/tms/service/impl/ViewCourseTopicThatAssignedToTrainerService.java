package edu.hcmuaf.tms.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.repository.TopicRepository;
import edu.hcmuaf.tms.repository.TrainerRepository;

@Service
@Transactional
public class ViewCourseTopicThatAssignedToTrainerService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	public DataTablesOutput<TopicForm> findAll(@Valid DataTablesInput input, long trainerID) {
		return topicRepository.findAll(input, null, new TopicSpecification(trainerID), (Topic t) -> TopicForm.toDTO(t));
	}

	private class TopicSpecification implements Specification<Topic> {
		private static final long serialVersionUID = 1L;
		private final Long trainerID;

		TopicSpecification(long trainerID) {
			this.trainerID = trainerID;
		}

		@Override
		public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			Expression<Trainer> trainer = root.get("trainer").as(Trainer.class);
			return criteriaBuilder.equal(trainer, trainerRepository.getOne(trainerID));

		}
	}

}
