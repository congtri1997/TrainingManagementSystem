package edu.hcmuaf.tms.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;

import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainer;

public class TrainerForm {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private String confirmPassword;
	private String birthDate;
	private String workingType;
	private String workingPlace;

	public TrainerForm() {
	}

	public TrainerForm(Trainer trainer) {
		id = trainer.getId();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		birthDate = trainer.getDateOfBirth().format(formatter);
		firstName = trainer.getFirstName();
		lastName = trainer.getLastName();
		userName = trainer.getUserName();
		email = trainer.getEmail();
		phone = trainer.getPhone();
		workingPlace = trainer.getWorkingPlace();
		workingType = trainer.getWorkingType();
	}

	public TrainerForm(long id, String firstName, String lastName, String userName, String email, String phone,
			String password, String confirmPassword, String birthDate, String workingType, String workingPlace) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.birthDate = birthDate;
		this.workingType = workingType;
		this.workingPlace = workingPlace;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
				+ userName + ", email=" + email + ", phone=" + phone + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", birthDate=" + birthDate + ", workingType=" + workingType + ", workingPlace="
				+ workingPlace + "]";
	}

	public Trainer doAddFirstTime(Role role, PasswordEncoder encoder) {
		Trainer trainer = new Trainer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainer.setDateOfBirth(LocalDate.parse(birthDate, formatter));
		trainer.setEmail(email);
		trainer.setEnabled(true);
		trainer.setEncryptedPassword(encoder.encode(password));
		trainer.setFirstName(firstName);
		trainer.setLastName(lastName);
		trainer.setPhone(phone);
		trainer.setWorkingPlace(workingPlace);
		trainer.setWorkingType(workingType);
		trainer.getRoles().add(role);
		trainer.setUserName(userName);

		return trainer;

	}

	public void update(Trainer trainer) {
		trainer.setEmail(email);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		trainer.setDateOfBirth(LocalDate.parse(birthDate, formatter));
		trainer.setFirstName(firstName);
		trainer.setLastName(lastName);
		trainer.setPhone(phone);
		trainer.setWorkingPlace(workingPlace);
		trainer.setWorkingType(workingType);
	}

}
