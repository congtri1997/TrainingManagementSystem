package temp;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstarctUserInformation extends AbstractUser {

	protected String firstName;
	protected String lastName;
	protected LocalDate birthDate;

	protected AbstarctUserInformation(long id, String userName, String encryptedPassword, boolean enabled, Role role,
			String firstName, String lastName, LocalDate birthDate) {
		super(id, userName, encryptedPassword, enabled, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

//	protected AbstarctUserInformation(long id, String userName, String encryptedPassword, boolean enabled, Set<Role> roles,
//			String firstName, String lastName, LocalDate birthDate) {
//		super(id, userName, encryptedPassword, enabled, roles);
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.birthDate = birthDate;
//	}

//	
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public LocalDate getBirthDate() {
//		return birthDate;
//	}
//
//	public void setBirthDate(LocalDate birthDate) {
//		this.birthDate = birthDate;
//	}

}
