package temp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class CourseCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courseCategories", cascade = CascadeType.ALL)
	private Set<Course> courses = new HashSet<Course>();

	public CourseCategory(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CourseCategory() {
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "CourseCategory [id=" + id + ", name=" + name + "]";
	}

}
