package edu.hcmuaf.tms.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>, DataTablesRepository<Feedback, Long> {

	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Feedback f WHERE f.topic.id = :topicID and f.trainee.id =:traineeID and f.feedbackType.id = :feedbackTypeID")
	boolean isFeedbackAlreadyExist(@Param("topicID") Long topicID, @Param("traineeID") Long traineeID,
			@Param("feedbackTypeID") Long feedbackTypeID);

	@Query("SELECT f FROM Feedback f where f.topic.id=:topicID and f.trainee.id = :traineeID and f.feedbackType.id = :feedbackTypeID")
	Feedback getFeedback(@Param("topicID") Long topicID, @Param("traineeID") Long traineeID,
			@Param("feedbackTypeID") Long feedbackTypeID);
	
	
	@Query("SELECT f FROM Feedback f where f.topic.id=:topicID and f.trainee.id = :traineeID")
	List<Feedback> getListFeedback(@Param("topicID") Long topicID, @Param("traineeID") Long traineeID);
	
}
