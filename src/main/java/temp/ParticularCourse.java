package temp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ParticularCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "courseID", nullable = false)
	private Course course;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "particularCourse", cascade = CascadeType.ALL)
	private Set<ParticularTopic> particularTopics = new HashSet<ParticularTopic>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "particularCourse", cascade = CascadeType.ALL)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();

}
