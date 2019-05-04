package edu.hcmuaf.tms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Trainer extends AbstarctUserInformation {

	private String workingType;
	private String workingPlace;
	private String phone;
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.ALL)
	private Set<ParticularTopic> particularTopics = new HashSet<ParticularTopic>();

	public Trainer() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<ParticularTopic> getParticularTopics() {
		return particularTopics;
	}

	public void setParticularTopics(Set<ParticularTopic> particularTopics) {
		this.particularTopics = particularTopics;
	}

}
