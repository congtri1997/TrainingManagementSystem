package edu.hcmuaf.tms.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long>, DataTablesRepository<Trainee, Long> {

	@Query("Select t from Trainee t where t.id not in( SELECT t.id from Trainee t join t.enrollments e where e.course.id = :courseID)")
	public List<Trainee> findTraineeNotInACourse(@Param("courseID") long courseID);
	@Query("Select t from Trainee t where t.id in( SELECT t.id from Trainee t join t.enrollments e where e.course.id = :courseID)")
	public List<Trainee> findTraineeInACourse(@Param("courseID") long courseID);

}
