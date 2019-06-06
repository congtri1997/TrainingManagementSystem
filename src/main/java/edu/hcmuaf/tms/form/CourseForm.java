package edu.hcmuaf.tms.form;

import edu.hcmuaf.tms.entity.Course;

public class CourseForm {

	private Long id;

	private String name;
	private String description;

	private String startDate;

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

	public static Course toDTO(CourseForm courseForm) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Course toEntity(CourseForm courseForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
