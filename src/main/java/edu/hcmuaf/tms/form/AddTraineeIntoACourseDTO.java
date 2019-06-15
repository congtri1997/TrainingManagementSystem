package edu.hcmuaf.tms.form;

import java.util.List;

public class AddTraineeIntoACourseDTO {

	private List<Long> traineeIDs;

	private Long courseID;

	@Override
	public String toString() {
		return "AddTraineeIntoACourseDTO [traineeIDs=" + traineeIDs + ", courseID=" + courseID + "]";
	}

	public List<Long> getTraineeIDs() {
		return traineeIDs;
	}

	public void setTraineeIDs(List<Long> traineeIDs) {
		this.traineeIDs = traineeIDs;
	}

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}
	
	
	
	
	
	

}
