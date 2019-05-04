package edu.hcmuaf.tms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Admin extends AbstractUser {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String userName, String encryptedPassword, boolean enabled) {
		super(userName, encryptedPassword, enabled);
	}

}
