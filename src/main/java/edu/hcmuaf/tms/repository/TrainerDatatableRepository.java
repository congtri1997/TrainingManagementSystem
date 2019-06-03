package edu.hcmuaf.tms.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import edu.hcmuaf.tms.entity.Trainer;

public interface TrainerDatatableRepository extends DataTablesRepository<Trainer, Long> {

}
