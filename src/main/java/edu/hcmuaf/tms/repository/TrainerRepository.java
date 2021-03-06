package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> , DataTablesRepository<Trainer, Long>{

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Trainer a WHERE a.email = :email")
	boolean isEmailAlreadyExisted(@Param("email") String email);

//	Trainer findByEmail(String email);
//	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Trainer t WHERE t.id = :id")
//	boolean isIdAlreadyExisted(@Param("id") Long id);
}
