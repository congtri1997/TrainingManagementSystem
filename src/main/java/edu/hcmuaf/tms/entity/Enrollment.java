package edu.hcmuaf.tms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "traineeID", nullable = false)
	private Trainee trainee;
	@ManyToOne
	@JoinColumn(name = "particularCourseID", nullable = false)
	private ParticularCourse particularCourse;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enrollment", cascade = CascadeType.ALL)
	private Set<Feedback> feedbacks = new HashSet<Feedback>();

}
