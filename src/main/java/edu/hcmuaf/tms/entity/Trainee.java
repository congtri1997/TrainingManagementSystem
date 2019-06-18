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
public class Trainee extends AbstarctUserInformation {

	private String education;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private ProgrammingLanguage programmingLanguage;
	private int scoreOfToeic;
	private String detailsOfExp;
	private String department;
	private String address;
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainee", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	
	@Builder
	public Trainee(long id, String userName, String encryptedPassword, boolean enabled, Role role, String firstName,
			String lastName, LocalDate birthDate, String education, ProgrammingLanguage programmingLanguage,
			int scoreOfToeic, String detailsOfExp, String department, String address) {
		super(id, userName, encryptedPassword, enabled, role, firstName, lastName, birthDate);
		this.education = education;
		this.programmingLanguage = programmingLanguage;
		this.scoreOfToeic = scoreOfToeic;
		this.detailsOfExp = detailsOfExp;
		this.department = department;
		this.address = address;
	}

	


}
