package temp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private LocalDate startDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Course_CourseCategory", joinColumns = {
			@JoinColumn(name = "courseID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "courseCategoryID", nullable = false, updatable = false) })
	private Set<CourseCategory> courseCategories = new HashSet<CourseCategory>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	private Set<ParticularCourse> particularCourses = new HashSet<ParticularCourse>();

	public Course(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Course() {
	}

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

	public Set<CourseCategory> getCourseCategories() {
		return courseCategories;
	}

	public void setCourseCategories(Set<CourseCategory> courseCategories) {
		this.courseCategories = courseCategories;
	}

	public Set<ParticularCourse> getParticularCourses() {
		return particularCourses;
	}

	public void setParticularCourses(Set<ParticularCourse> particularCourses) {
		this.particularCourses = particularCourses;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
