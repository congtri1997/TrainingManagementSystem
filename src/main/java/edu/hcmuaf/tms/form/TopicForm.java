package edu.hcmuaf.tms.form;

import java.time.format.DateTimeFormatter;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.entity.Trainer;

public class TopicForm {

	private Long id;

	private String name;

	private String startDate;

	private String duration;

	private Trainer trainer;

	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public static TopicForm toDTO(Topic t) {
		TopicForm topicForm = new TopicForm();
		topicForm.setId(t.getId());
		topicForm.setCourse(t.getCourse());
		topicForm.setDuration(t.getDuration() + "");
		topicForm.setName(t.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		topicForm.setStartDate(formatter.format(t.getStartDate()));
		topicForm.setTrainer(t.getTrainer());

		return topicForm;
	}

	@Override
	public String toString() {
		return "TopicForm [id=" + id + ", name=" + name + ", startDate=" + startDate + ", duration=" + duration
				+ ", trainer=" + trainer + ", course=" + course + "]";
	}

}
