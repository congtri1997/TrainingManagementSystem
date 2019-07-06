package edu.hcmuaf.tms.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.form.CourseCategoryForm;
import edu.hcmuaf.tms.repository.CourseCategoryRepository;

@Service
@Transactional
public class CourseCategoryService {

	@Autowired
	private CourseCategoryRepository courseCategoryRepository;

	public void addCourseCategory(CourseCategoryForm courseCategoryForm) {
		CourseCategory courseCategory = CourseCategoryForm.toEntity(courseCategoryForm);

		courseCategoryRepository.save(courseCategory);
	}

	public void updateCourseCategory(CourseCategoryForm courseCategoryForm) {
		if (courseCategoryRepository.existsById(courseCategoryForm.getId())) {
			CourseCategory courseCategory = courseCategoryRepository.getOne(courseCategoryForm.getId());
			courseCategory.setName(courseCategoryForm.getName());
			courseCategoryRepository.save(courseCategory);
		}
	}

	public List<CourseCategory> listAll() {
		return courseCategoryRepository.findAll();
	}

	public CourseCategory getOne(Long id) {
		if (id == null || !courseCategoryRepository.existsById(id))
			return null;
		CourseCategory courseCategory = courseCategoryRepository.getOne(id);
		return courseCategory;
	}

	public void delete(long id) {
		if (courseCategoryRepository.existsById(id)) {
			courseCategoryRepository.delete(courseCategoryRepository.getOne(id));
		}
	}

	public boolean existsById(long id) {
		return courseCategoryRepository.existsById(id);
	}

	public DataTablesOutput<CourseCategoryForm> findAll(@Valid DataTablesInput input) {
		return courseCategoryRepository.findAll(input, null, null, (CourseCategory c) -> CourseCategoryForm.toDTO(c));
	}


}
