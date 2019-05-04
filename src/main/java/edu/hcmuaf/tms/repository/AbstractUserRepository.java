package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.AbstractUser;

public interface AbstractUserRepository extends JpaRepository<AbstractUser, Long> {
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AbstractUser a WHERE a.userName = :username")
	boolean isUserNameAlreadyExisted(@Param("username") String username);
//
//	AbstractUser findByUserName(String userName);

}
