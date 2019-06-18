package edu.hcmuaf.tms.form;

import edu.hcmuaf.tms.entity.Staff;

public class StaffForm {

	private Long id;
	private String userName;
	private String password;
	private String confirmPassword;


	public StaffForm() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public static StaffForm toDTO(Staff staff) {
		StaffForm staffForm = new StaffForm();
		staffForm.setPassword(staff.getEncryptedPassword());
		staffForm.setUserName(staff.getUserName());
		staffForm.setId(staff.getId());
		return staffForm;
	}


}
