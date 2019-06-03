package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.ProgrammingLanguage;

public interface ProgrammingLanguagRepository extends JpaRepository<ProgrammingLanguage, Long> {
	
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProgrammingLanguage p WHERE p.name = :name")
	boolean isProgrammingLanguageAlreadyExist(@Param("name") String name);

	ProgrammingLanguage findByName(String name);
	
}
