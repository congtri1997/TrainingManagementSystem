package edu.hcmuaf.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.WorkingType;
import edu.hcmuaf.tms.repository.WorkingTypeRepository;

@Service
@Transactional
public class WorkingTypeService {

	@Autowired
	private WorkingTypeRepository workingTypeRepository;

	public void add(WorkingType workingType) {
		workingTypeRepository.save(workingType);
	}

	public boolean isWorkingTypeAlreadyExist(String name) {
		return workingTypeRepository.isWorkingTypeAlreadyExist(name);
	}

	public void addIfNotExist(String name) {
		if (!isWorkingTypeAlreadyExist(name)) {
			add(new WorkingType(name));
		}
	}

	public List<WorkingType> findAll() {
		return workingTypeRepository.findAll();
	}

	public WorkingType findById(Long id) {
		if (workingTypeRepository.existsById(id)) {
			return workingTypeRepository.getOne(id);
		}
		return null;
	}

	public WorkingType findByName(String name) {
		return workingTypeRepository.findByName(name);
	}
}
