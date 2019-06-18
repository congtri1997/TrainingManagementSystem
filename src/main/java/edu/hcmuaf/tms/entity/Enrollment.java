package edu.hcmuaf.tms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Enrollment {
	public Enrollment(Course course, Trainee trainee) {
		this.course = course;
		this.trainee = trainee;
	}
	
	public Enrollment() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "traineeID", nullable = false)
	private Trainee trainee;
	@ManyToOne
	@JoinColumn(name = "courseID", nullable = false)
	private Course course;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enrollment", cascade = CascadeType.ALL)
//	private Set<Feedback> feedbacks = new HashSet<Feedback>();
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	
	
}
