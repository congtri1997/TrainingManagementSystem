package edu.hcmuaf.tms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.form.CourseForm;
import edu.hcmuaf.tms.form.TopicForm;
import edu.hcmuaf.tms.repository.CourseRepository;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public void addCourse(CourseForm courseForm) {
		Course course = CourseForm.toEntity(courseForm);
		/*
		 * do sth here
		 */

		courseRepository.save(course);
	}

	public void updateCourse(CourseForm courseForm) {
		if (courseRepository.existsById(courseForm.getId())) {
			Course course = courseRepository.getOne(courseForm.getId());
			course.setName(courseForm.getName());
			course.setDescription(courseForm.getDescription());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			if (courseForm.getStartDate() != null)
				course.setStartDate(LocalDate.parse(courseForm.getStartDate(), formatter));

			courseRepository.save(course);
		}
	}

	public List<Course> listAll() {
		return courseRepository.findAll();
	}

	public Course getOne(Long id) {
		if (id == null || !courseRepository.existsById(id))
			return null;
		Course course = courseRepository.getOne(id);
		return course;
	}

	public void delete(long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.delete(courseRepository.getOne(id));
		}
	}

	public boolean existsById(long id) {
		return courseRepository.existsById(id);
	}

	public DataTablesOutput<CourseForm> findAll(@Valid DataTablesInput input) {
		return courseRepository.findAll(input, null, null, (Course c) -> CourseForm.toDTO(c));
	}

	public void addTopicIntoACourse(TopicForm topicForm) {
		if (topicForm.getCourse() != null && courseRepository.existsById(topicForm.getCourse().getId())) {
			Course course = courseRepository.getOne(topicForm.getCourse().getId());
			Topic topic = new Topic();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			topic.setName(topicForm.getName());
			topic.setDuration(Integer.parseInt(topicForm.getDuration()));
			topic.setStartDate(LocalDate.parse(topicForm.getStartDate(), formatter));
			course.getTopics().add(topic);
			topic.setCourse(course);
			courseRepository.save(course);
		}
	}

}
