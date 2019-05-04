package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hcmuaf.tms.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
