package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.hcmuaf.tms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>, DataTablesRepository<Course, Long> {

}
