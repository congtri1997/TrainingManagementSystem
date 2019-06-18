package edu.hcmuaf.tms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.hcmuaf.tms.entity.Topic;
import edu.hcmuaf.tms.form.ChartTrainer;

public interface TopicRepository extends JpaRepository<Topic, Long>, DataTablesRepository<Topic, Long> {

	@Query("SELECT  new edu.hcmuaf.tms.form.ChartTrainer(t.trainer, count(t.id)) FROM Topic t GROUP BY t.trainer.id ORDER BY count(t.id)")
	List<ChartTrainer> getTopTrainerWhoTeachMost(Pageable pageable);

	@Query("SELECT  new edu.hcmuaf.tms.form.ChartTrainer(t.trainer, count(t.id)) FROM Topic t WHERE t.startDate between :start AND :end GROUP BY t.trainer.id ORDER BY count(t.id)")
	List<ChartTrainer> getTopTrainerWhoTeachMostBetween(Pageable pageable, @Param("start") LocalDate startDate,
			@Param("end") LocalDate endDate);
	@Query("SELECT  new edu.hcmuaf.tms.form.ChartTrainer(t.trainer, count(t.id)) FROM Topic t WHERE t.startDate >= :start GROUP BY t.trainer.id ORDER BY count(t.id)")
	List<ChartTrainer> getTopTrainerWhoTeachMostGreaterThanEqual(Pageable pageable,@Param("start") LocalDate startDate);

	
	@Query("SELECT  new edu.hcmuaf.tms.form.ChartTrainer(t.trainer, count(t.id)) FROM Topic t WHERE t.startDate <= :end GROUP BY t.trainer.id ORDER BY count(t.id)")
	List<ChartTrainer> getTopTrainerWhoTeachMostLessThanOrEqual(Pageable pageable,@Param("end") LocalDate endDate);

}
