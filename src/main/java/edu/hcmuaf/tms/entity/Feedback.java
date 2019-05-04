package edu.hcmuaf.tms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "enrollmentID", nullable = false)
	private Enrollment enrollment;
	@ManyToOne
	@JoinColumn(name = "feedbackTypeID", nullable = false)
	private FeedbackType feedbackType;

}
