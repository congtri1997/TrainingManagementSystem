package edu.hcmuaf.tms.form;

import java.util.List;

public class ImportTraineeAccountDTO {

	private List<TraineeForm> traineeForms;

	public List<TraineeForm> getTraineeForms() {
		return traineeForms;
	}

	public void setTraineeForms(List<TraineeForm> traineeForms) {
		this.traineeForms = traineeForms;
	}

	@Override
	public String toString() {
		return "ImportTraineeAccountDTO [traineeForms=" + traineeForms + "]";
	}
	


}
