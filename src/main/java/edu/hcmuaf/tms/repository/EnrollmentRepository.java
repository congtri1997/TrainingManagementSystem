package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, DataTablesRepository<Enrollment, Long> {

	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Enrollment e WHERE e.course.id = :courseID and e.trainee.id = :traineeID")
	boolean isEnrollmentAlreadyExist(@Param("courseID") Long courseID, @Param("traineeID") Long traineeID);
	@Query("SELECT e FROM Enrollment e where e.course.id = :courseID and e.trainee.id = :traineeID")
	Enrollment getEnrollment(@Param("courseID") Long courseID, @Param("traineeID") Long traineeID);
	
}
