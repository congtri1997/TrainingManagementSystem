package edu.hcmuaf.tms.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.form.CourseForm;
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
	
	
	
}
