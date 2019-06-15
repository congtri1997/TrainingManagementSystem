package edu.hcmuaf.tms.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.Topic;

public class CourseForm {

	private Long id;

	private String name;
	private String description;

	private String startDate;

	private List<TopicForm> topics = new ArrayList<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<TopicForm> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicForm> topics) {
		this.topics = topics;
	}

	public static CourseForm toDTO(Course course) {
		CourseForm courseForm = new CourseForm();
		courseForm.setId(course.getId());
		courseForm.setDescription(course.getDescription());
		courseForm.setName(course.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		courseForm.setStartDate(formatter.format(course.getStartDate()));
		return courseForm;
	}

	public static Course toEntity(CourseForm courseForm) {
		Course course = new Course();
		course.setName(courseForm.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (courseForm.getStartDate() != null)
			course.setStartDate(LocalDate.parse(courseForm.getStartDate(), formatter));
		course.setDescription(courseForm.getDescription());
		return course;
	}

	public static CourseForm toDTOWithTopic(Course course) {
		if (course != null) {
			CourseForm courseForm = CourseForm.toDTO(course);
			for (Topic t : course.getTopics()) {
				TopicForm tpf = new TopicForm();
				tpf.setId(t.getId());
				tpf.setName(t.getName());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				if (t.getStartDate() != null) {
					tpf.setStartDate(formatter.format(t.getStartDate()));
				}
				if (t.getTrainer() != null) {
					tpf.setTrainer(t.getTrainer());
				}
				tpf.setDuration(t.getDuration()+"");
				courseForm.getTopics().add(tpf);
			}

			return courseForm;
		}
		return null;
	}

}
