package edu.hcmuaf.tms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ProgrammingLanguage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProgrammingLanguage(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ProgrammingLanguage() {
	}

	public ProgrammingLanguage(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProgramingLanguage [id=" + id + ", name=" + name + "]";
	}

}
