package edu.hcmuaf.tms.form;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.Trainee;

public class TraineeForm {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String confirmPassword;
	private String birthDate;
	private String education;
	private String scoreOfToeic;
	private String detailsOfExp;
	private String department;
	private String address;
	private int number;

	private ProgrammingLanguage programmingLanguage;
	private String passwordText;

	private List<Course> courses = new ArrayList<Course>();

//	public static TraineeForm convertToTraineeForm(Trainee trainee) {
//		TraineeForm traineeForm = new TraineeForm();
//		traineeForm.setId(trainee.getId());
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		traineeForm.setBirthDate(formatter.format(trainee.getBirthDate()));
//		traineeForm.setFirstName(trainee.getFirstName());
//		traineeForm.setLastName(trainee.getLastName());
//		traineeForm.setPasswordText(trainee.getEncryptedPassword());
//		traineeForm.setUserName(trainee.getUserName());
//		traineeForm.setAddress(trainee.getAddress());
//		traineeForm.setDetailsOfExp(trainee.getDetailsOfExp());
//		traineeForm.setScoreOfToeic(trainee.getScoreOfToeic() + "");
//		traineeForm.setDepartment(trainee.getDepartment());
//		traineeForm.setEducation(trainee.getEducation());
//		traineeForm.setPasswordText(trainee.getEncryptedPassword());
//		traineeForm.setProgrammingLanguage(
//				trainee.getProgrammingLanguage() != null ? trainee.getProgrammingLanguage().getId() + "" : null);
//		traineeForm.setProgrammingLanguageText(
//				trainee.getProgrammingLanguage() != null ? trainee.getProgrammingLanguage().getName() : null);
//		return traineeForm;
//	}

	public TraineeForm() {
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getScoreOfToeic() {
		return scoreOfToeic;
	}

	public void setScoreOfToeic(String scoreOfToeic) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ProgrammingLanguage getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	@Override
	public String toString() {
		return "TraineeForm [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", confirmPassword=" + confirmPassword + ", birthDate="
				+ birthDate + ", education=" + education + ", scoreOfToeic=" + scoreOfToeic + ", detailsOfExp="
				+ detailsOfExp + ", department=" + department + ", address=" + address + ", number=" + number
				+ ", programmingLanguage=" + programmingLanguage + ", passwordText=" + passwordText + "]";
	}

	public static TraineeForm toDTO(Trainee trainee) {
		TraineeForm traineeForm = new TraineeForm();
		traineeForm.setId(trainee.getId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (trainee.getBirthDate() != null)
			traineeForm.setBirthDate(formatter.format(trainee.getBirthDate()));
		traineeForm.setFirstName(trainee.getFirstName());
		traineeForm.setLastName(trainee.getLastName());
		traineeForm.setPasswordText(trainee.getEncryptedPassword());
		traineeForm.setUserName(trainee.getUserName());
		traineeForm.setAddress(trainee.getAddress());
		traineeForm.setDetailsOfExp(trainee.getDetailsOfExp());
		traineeForm.setScoreOfToeic(trainee.getScoreOfToeic() + "");
		traineeForm.setDepartment(trainee.getDepartment());
		traineeForm.setEducation(trainee.getEducation());
		traineeForm.setPasswordText(trainee.getEncryptedPassword());
		if (trainee.getProgrammingLanguage() != null) {
			traineeForm.setProgrammingLanguage(trainee.getProgrammingLanguage());

		}
		return traineeForm;
	}

	public static TraineeForm toDTOWithTopics(Trainee trainee) {
		TraineeForm traineeForm = toDTO(trainee);
		for (Enrollment enrollment : trainee.getEnrollments()) {
			traineeForm.getCourses().add(enrollment.getCourse());
		}
		return traineeForm;
	}

}
