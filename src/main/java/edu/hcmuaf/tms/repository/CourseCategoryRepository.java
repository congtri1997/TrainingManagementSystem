package edu.hcmuaf.tms.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.entity.Trainee;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long>, DataTablesRepository<CourseCategory, Long> {

}
