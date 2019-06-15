package edu.hcmuaf.tms.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trainer extends AbstarctUserInformation {

	@ManyToOne(cascade = CascadeType.PERSIST)
	private WorkingType workingType;
	private String workingPlace;
	private String phoneNumber;
	private String email;
	@Builder.Default
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.ALL)
	private Set<Topic> topics = new HashSet<Topic>();

	@Builder
	public Trainer(long id, String userName, String encryptedPassword, boolean enabled, Role role, String firstName,
			String lastName, LocalDate birthDate, WorkingType workingType, String workingPlace, String phoneNumber,
			String email) {
		super(id, userName, encryptedPassword, enabled, role, firstName, lastName, birthDate);
		this.workingType = workingType;
		this.workingPlace = workingPlace;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

}
