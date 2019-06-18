package edu.hcmuaf.tms.service.impl;

import java.util.List;

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

import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.Feedback;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.FeedbackDTO;
import edu.hcmuaf.tms.form.FeedbackHolder;
import edu.hcmuaf.tms.repository.EnrollmentRepository;
import edu.hcmuaf.tms.repository.FeedbackRepository;
import edu.hcmuaf.tms.repository.FeedbackTypeRepository;
import edu.hcmuaf.tms.repository.TopicRepository;
import edu.hcmuaf.tms.repository.TraineeRepository;

@Service
@Transactional
public class FeedbackService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private FeedbackTypeRepository feedbackTypeRepository;

	public DataTablesOutput<Enrollment> findAll(@Valid DataTablesInput input, long traineeID) {
		return enrollmentRepository.findAll(input, null, new EnrollmentSpecification(traineeID));
	}

	private class EnrollmentSpecification implements Specification<Enrollment> {
		private static final long serialVersionUID = 1L;
		private final Long traineeID;

		EnrollmentSpecification(long traineeID) {
			this.traineeID = traineeID;
		}

		@Override
		public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			Expression<Trainee> trainee = root.get("trainee").as(Trainee.class);
			return criteriaBuilder.equal(trainee, traineeRepository.getOne(traineeID));

		}
	}
//	public DataTablesOutput<TopicForm> findAll(@Valid DataTablesInput input, long traineeID) {
//		return topicRepository.findAll(input, null, new TopicSpecification(traineeID), (Topic t) -> TopicForm.toDTO(t));
//	}
//	
//	private class TopicSpecification implements Specification<Topic> {
//		private static final long serialVersionUID = 1L;
//		private final Long traineeID;
//		
//		TopicSpecification(long traineeID) {
//			this.traineeID = traineeID;
//		}
//		
//		@Override
//		public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//			Expression<Trainee> trainee = root.get("course.enrollments.trainee").as(Trainee.class);
//			return criteriaBuilder.equal(trainee, traineeRepository.getOne(traineeID));
//			
//		}
//	}

	public void doFeedback(FeedbackDTO feedbackDTO, long traineeID) {
		long topicID = feedbackDTO.getTopicID();
		if (traineeRepository.existsById(traineeID) && (topicRepository.existsById(topicID))) {
			for (FeedbackHolder feedbackHolder : feedbackDTO.getRatings()) {
				Feedback feedback = null;
				if (feedbackRepository.isFeedbackAlreadyExist(topicID, traineeID, feedbackHolder.getId())) {
					feedback = feedbackRepository.getFeedback(topicID, traineeID, feedbackHolder.getId());
					feedback.setRating(feedbackHolder.getRating());
				} else {
					feedback = new Feedback();
					feedback.setFeedbackType(feedbackTypeRepository.getOne(feedbackHolder.getId()));
					feedback.setRating(feedbackHolder.getRating());
					feedback.setTrainee(traineeRepository.getOne(traineeID));
					feedback.setTopic(topicRepository.getOne(topicID));
				}
				feedbackRepository.save(feedback);
			}
		}
	}
	
	public List<Feedback> getListFeedback(long topicID,long traineeID) {
		return feedbackRepository.getListFeedback(topicID, traineeID);
				
	}

}
