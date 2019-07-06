package edu.hcmuaf.tms.form;

import java.util.List;

public class AddCourseCategoryIntoACourseDTO {

	private List<Long> courseCategoryIDs;

	private Long courseID;

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}

	public List<Long> getCourseCategoryIDs() {
		return courseCategoryIDs;
	}

	public void setCourseCategoryIDs(List<Long> courseCategoryIDs) {
		this.courseCategoryIDs = courseCategoryIDs;
	}

	@Override
	public String toString() {
		return "AddCourseCategoryIntoACourseDTO [courseCategoryIDs=" + courseCategoryIDs + ", courseID=" + courseID
				+ "]";
	}

}
