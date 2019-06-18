package edu.hcmuaf.tms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {

	private String username;
	@Id
	private String series;
	private String token;
	private LocalDate last_used;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getLast_used() {
		return last_used;
	}

	public void setLast_used(LocalDate last_used) {
		this.last_used = last_used;
	}

}
