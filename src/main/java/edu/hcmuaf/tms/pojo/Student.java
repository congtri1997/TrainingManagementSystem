package edu.hcmuaf.tms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentID")
	private long studentID;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "firstName")
	private String firstName;
}
