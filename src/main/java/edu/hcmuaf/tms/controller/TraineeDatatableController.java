package edu.hcmuaf.tms.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.repository.TraineeDatatableRepository;

@RestController
public class TraineeDatatableController {

	@Autowired
	private TraineeDatatableRepository traineeDatatableRepository;

	@RequestMapping(value = "/staff/trainee/trainees", method = RequestMethod.GET)
	public DataTablesOutput<Trainee> list(@Valid DataTablesInput input) {
		System.out.println("before return");
		DataTablesOutput<Trainee> result = traineeDatatableRepository.findAll(input,new ScoreOfToeicSpecification(input));
		System.out.println("result: " + result.getError());
		System.out.println("after return");
		return result;
	}


	private class ScoreOfToeicSpecification implements Specification<Trainee> {
		private final Integer minScore;
		private final Integer maxScore;

		ScoreOfToeicSpecification(DataTablesInput input) {
			String scoreFilter = input.getColumn("scoreOfToeic").getSearch().getValue();
			if (!StringUtils.hasText(scoreFilter)) {
				minScore = maxScore = null;
				return;
			}
			String[] bounds = scoreFilter.split(";");
			minScore = getValue(bounds, 0);
			maxScore = getValue(bounds, 1);
		}

		private Integer getValue(String[] bounds, int index) {
			if (bounds.length > index && StringUtils.hasText(bounds[index])) {
				try {
					return Integer.valueOf(bounds[index]);
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return null;
		}

		@Override
		public Predicate toPredicate(Root<Trainee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			Expression<Integer> scoreOfToeic = root.get("scoreOfToeic").as(Integer.class);
			System.out.println(minScore + " - "+ maxScore);
			if (minScore != null && maxScore != null) {
				return criteriaBuilder.between(scoreOfToeic, minScore, maxScore);
			} else if (minScore != null) {
				return criteriaBuilder.greaterThanOrEqualTo(scoreOfToeic, minScore);
			} else if (maxScore != null) {
				return criteriaBuilder.lessThanOrEqualTo(scoreOfToeic, maxScore);
			} else {
				return criteriaBuilder.conjunction();
			}
		}
	}

}
