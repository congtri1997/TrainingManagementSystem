package edu.hcmuaf.tms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false, unique = true)
	protected String userName;

	protected String encryptedPassword;

	protected boolean enabled;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "User_Role", joinColumns = {
			@JoinColumn(name = "userID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "roleID", nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>();

	protected AbstractUser() {
	}

	public AbstractUser(String userName, String encryptedPassword, boolean enabled) {
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
