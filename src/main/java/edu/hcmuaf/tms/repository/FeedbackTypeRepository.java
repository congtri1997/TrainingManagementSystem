package edu.hcmuaf.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.hcmuaf.tms.entity.FeedbackType;

@Repository
public interface FeedbackTypeRepository  extends JpaRepository<FeedbackType, Long> {
	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FeedbackType f WHERE f.name = :name")
	boolean isFeedbackTypeAlreadyExist(@Param("name") String name);
	
	List<FeedbackType> findAll();
	
	FeedbackType findByName(String name);
	
	
}
