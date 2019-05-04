package edu.hcmuaf.tms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Trainee extends AbstarctUserInformation {

	private String education;
	@ManyToOne
	@JoinColumn(name = "mainProgrammingID")
	private ProgramingLanguage programingLanguage;
	private int scoreOfToeic;
	private String detailsOfExp;
	private String department;
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainee", cascade = CascadeType.ALL)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();



	public Trainee() {
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public ProgramingLanguage getProgramingLanguage() {
		return programingLanguage;
	}

	public void setProgramingLanguage(ProgramingLanguage programingLanguage) {
		this.programingLanguage = programingLanguage;
	}

	public int getScoreOfToeic() {
		return scoreOfToeic;
	}

	public void setScoreOfToeic(int scoreOfToeic) {
		this.scoreOfToeic = scoreOfToeic;
	}

	public String getDetailsOfExp() {
		return detailsOfExp;
	}

	public void setDetailsOfExp(String detailsOfExp) {
		this.detailsOfExp = detailsOfExp;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
