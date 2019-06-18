package edu.hcmuaf.tms.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.form.ChartTrainer;
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
//
//	public List<ChartTrainer> getTopTrainerWhoTeachTheMost(int n, LocalDate startDate, LocalDate endDate) {
//		return topicRepository.getTopTrainerWhoTeachMost( new Specification<Topic>() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				System.out.println("Cong Tri");
//				Expression<LocalDate> date = root.get("startDate").as(LocalDate.class);
//				if (startDate != null && endDate != null) {
//					return criteriaBuilder.between(date, startDate, endDate);
//				} else if (startDate != null) {
//					return criteriaBuilder.greaterThanOrEqualTo(date, startDate);
//				} else if (endDate != null) {
//					return criteriaBuilder.lessThanOrEqualTo(date, endDate);
//				} else {
//					return criteriaBuilder.conjunction();
//				}
//			}
//		},PageRequest.of(0, n));
//	}

	public List<ChartTrainer> getTopTrainerWhoTeachTheMost(int n, LocalDate startDate, LocalDate endDate) {
		if (startDate != null && endDate != null)
			return topicRepository.getTopTrainerWhoTeachMostBetween(PageRequest.of(0, n), startDate, endDate);
		else if (startDate != null)
			return topicRepository.getTopTrainerWhoTeachMostGreaterThanEqual(PageRequest.of(0, n), startDate);
		else if (endDate != null)
			return topicRepository.getTopTrainerWhoTeachMostLessThanOrEqual(PageRequest.of(0, n), endDate);
		return topicRepository.getTopTrainerWhoTeachMost(PageRequest.of(0, n));
	}

}
