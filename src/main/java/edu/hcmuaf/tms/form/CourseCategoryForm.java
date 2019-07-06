package edu.hcmuaf.tms.form;

import edu.hcmuaf.tms.entity.CourseCategory;

public class CourseCategoryForm {

	private Long id;

	private String name;

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

	public static CourseCategoryForm toDTO(CourseCategory courseCategory) {
		CourseCategoryForm courseForm = new CourseCategoryForm();
		courseForm.setId(courseCategory.getId());
		courseForm.setName(courseCategory.getName());
		return courseForm;
	}

	public static CourseCategory toEntity(CourseCategoryForm courseForm) {
		CourseCategory courseCategory = new CourseCategory();
		courseCategory.setName(courseForm.getName());
		return courseCategory;
	}

}
