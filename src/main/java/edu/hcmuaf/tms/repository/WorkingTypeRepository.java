package edu.hcmuaf.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.WorkingType;

public interface WorkingTypeRepository extends JpaRepository<WorkingType, Long> {

	@Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WorkingType w WHERE w.name = :name")
	boolean isWorkingTypeAlreadyExist(@Param("name") String name);
	
	List<WorkingType> findAll();
	
	WorkingType findByName(String name);
}
