package edu.hcmuaf.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.WorkingType;
import edu.hcmuaf.tms.repository.ProgrammingLanguagRepository;

@Service
@Transactional
public class ProgrammingLanguageService {

	@Autowired
	private ProgrammingLanguagRepository programmingLanguagRepository;

	public void add(ProgrammingLanguage programingLanguage) {
		programmingLanguagRepository.save(programingLanguage);
	}

	public boolean isProgrammingLanguageAlreadyExist(String name) {
		return programmingLanguagRepository.isProgrammingLanguageAlreadyExist(name);
	}

	public ProgrammingLanguage findById(Long programmingLangeuageID) {
		if (programmingLanguagRepository.existsById(programmingLangeuageID)) {
			return programmingLanguagRepository.getOne(programmingLangeuageID);
		}
		return null;
	}
	

	public void addIfNotExist(String name) {
		if (!isProgrammingLanguageAlreadyExist(name)) {
			add(new ProgrammingLanguage(name));
		}
	}

	public ProgrammingLanguage findByName(String name) {
		return programmingLanguagRepository.findByName(name);
	}
	
	
	public List<ProgrammingLanguage> findAll() {
		return programmingLanguagRepository.findAll();
	}

}
