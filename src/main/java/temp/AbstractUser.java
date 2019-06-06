package temp;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false, unique = true)
	protected String userName;

	protected String encryptedPassword;

	protected boolean enabled;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "User_Role", joinColumns = {
			@JoinColumn(name = "userID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "roleID", nullable = false, updatable = false) })
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	public AbstractUser(long id, String userName, String encryptedPassword, boolean enabled, Role role) {
		this.id = id;
		this.userName = userName;
		this.enabled = enabled;
		this.encryptedPassword = encryptedPassword;
		this.roles.add(role);
	}

}
