package edu.hcmuaf.tms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.form.AddCourseCategoryIntoACourseDTO;
import edu.hcmuaf.tms.repository.CourseCategoryRepository;
import edu.hcmuaf.tms.repository.CourseRepository;

@Service
@Transactional
public class AddCourseCategoryIntoCourseService {

	@Autowired
	private CourseCategoryRepository courseCategoryRepository;

	@Autowired
	private CourseRepository courseRepository;

	public void addCourseCategoryIntoCourse(AddCourseCategoryIntoACourseDTO addCourseCategoryIntoACourseDTO) {
		Long courseID = addCourseCategoryIntoACourseDTO.getCourseID();
		if (courseID != null && courseRepository.existsById(courseID)) {
			Course course = courseRepository.getOne(courseID);
			course.getCourseCategories().clear();
			for (Long courseCategoryID : addCourseCategoryIntoACourseDTO.getCourseCategoryIDs()) {
				if (courseCategoryID != null && courseCategoryRepository.existsById(courseCategoryID)) {
					course.getCourseCategories().add(courseCategoryRepository.getOne(courseCategoryID));
				}
			}
			courseRepository.save(course);
		}
	}

	public List<CourseCategory> getListCourseCategory(Long courseID) {
		List<CourseCategory> result = new ArrayList<>();
		if (courseID != null && courseRepository.existsById(courseID)) {
			Course course = courseRepository.getOne(courseID);
			for (CourseCategory courseCategory : course.getCourseCategories()) {
				result.add(courseCategory);
			}
		}
		return result;
	}
}
