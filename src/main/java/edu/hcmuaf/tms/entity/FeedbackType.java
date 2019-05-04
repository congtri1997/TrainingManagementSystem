package edu.hcmuaf.tms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class FeedbackType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feedbackType", cascade = CascadeType.ALL)
	private Set<Feedback> feedbacks = new HashSet<Feedback>();

	public FeedbackType() {
	}

	public FeedbackType(long id, String name, Set<Feedback> feedbacks) {
		this.id = id;
		this.name = name;
		this.feedbacks = feedbacks;
	}

	public FeedbackType(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}
