package edu.hcmuaf.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.FeedbackType;
import edu.hcmuaf.tms.repository.FeedbackTypeRepository;

@Service
@Transactional
public class FeedbackTypeService {
	@Autowired
	private FeedbackTypeRepository feedbackTypeRepository;

	public void add(FeedbackType feedbackType) {
		feedbackTypeRepository.save(feedbackType);
	}

	public boolean isFeedbackTypeAlreadyExist(String name) {
		return feedbackTypeRepository.isFeedbackTypeAlreadyExist(name);
	}

	public void addIfNotExist(String name) {
		if (!isFeedbackTypeAlreadyExist(name)) {
			add(new FeedbackType(name));
		}
	}

	public List<FeedbackType> findAll() {
		return feedbackTypeRepository.findAll();
	}

	public FeedbackType findById(Long id) {
		if (feedbackTypeRepository.existsById(id)) {
			return feedbackTypeRepository.getOne(id);
		}
		return null;
	}

	public FeedbackType findByName(String name) {
		return feedbackTypeRepository.findByName(name);
	}
}
