package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import edu.hcmuaf.tms.entity.Trainee;

public interface TraineeDatatableRepository extends DataTablesRepository<Trainee, Long> {

}
