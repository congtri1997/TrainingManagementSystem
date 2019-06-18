package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.hcmuaf.tms.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>, DataTablesRepository<Staff, Long> {

}
