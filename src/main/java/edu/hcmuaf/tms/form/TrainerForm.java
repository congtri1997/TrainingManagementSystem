package edu.hcmuaf.tms.form;

import java.time.format.DateTimeFormatter;

import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.entity.WorkingType;

public class TrainerForm {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String phoneNumber;
	private String password;
	private String confirmPassword;
	private String birthDate;
	private String workingType;
	private String workingPlace;

	private String workingTypeText;
	
	private String passwordText;

	public static TrainerForm convertToTrainerForm(Trainer trainer) {
		TrainerForm trainerForm = new TrainerForm();
		trainerForm.setId(trainer.getId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainerForm.setBirthDate(formatter.format(trainer.getBirthDate()));
		trainerForm.setEmail(trainer.getEmail());
		trainerForm.setFirstName(trainer.getFirstName());
		trainerForm.setLastName(trainer.getLastName());
		trainerForm.setPasswordText(trainer.getEncryptedPassword());
		trainerForm.setPhoneNumber(trainer.getPhoneNumber());
		trainerForm.setUserName(trainer.getUserName());
		trainerForm.setWorkingPlace(trainer.getWorkingPlace());
		WorkingType workingType = trainer.getWorkingType();
		trainerForm.setWorkingType(workingType == null ? null : (workingType.getId() + ""));
		trainerForm.setWorkingTypeText(workingType == null ? null : (workingType.getName() + ""));
		return trainerForm;
	}

	public TrainerForm() {
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
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

	public String getWorkingType() {
		return workingType;
	}

	public void setWorkingType(String workingType) {
		this.workingType = workingType;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "TrainerForm [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", phone=" + phoneNumber + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", birthDate=" + birthDate + ", workingType=" + workingType
				+ ", workingPlace=" + workingPlace + "]";
	}

	public String getWorkingTypeText() {
		return workingTypeText;
	}

	public void setWorkingTypeText(String workingTypeText) {
		this.workingTypeText = workingTypeText;
	}

//	public Trainer doAddFirstTime(Role role, PasswordEncoder encoder) {
//		Trainer trainer = new Trainer();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		trainer.setDateOfBirth(LocalDate.parse(birthDate, formatter));
//		trainer.setEmail(email);
//		trainer.setEnabled(true);
//		trainer.setEncryptedPassword(encoder.encode(password));
//		trainer.setFirstName(firstName);
//		trainer.setLastName(lastName);
//		trainer.setPhone(phoneNumber);
//		trainer.setWorkingPlace(workingPlace);
////		trainer.setWorkingType(workingType.);
//		trainer.getRoles().add(role);
//		trainer.setUserName(userName);
//
//		return trainer;
//
//	}

//	public void update(Trainer trainer) {
//		trainer.setEmail(email);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		trainer.setDateOfBirth(LocalDate.parse(birthDate, formatter));
//		trainer.setFirstName(firstName);
//		trainer.setLastName(lastName);
//		trainer.setPhone(phoneNumber);
//		trainer.setWorkingPlace(workingPlace);
////		trainer.setWorkingType(workingType);
//	}

}
