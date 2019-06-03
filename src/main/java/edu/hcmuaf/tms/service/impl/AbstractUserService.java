package edu.hcmuaf.tms.service.impl;

import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.AbstractUser;
import edu.hcmuaf.tms.repository.AbstractUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AbstractUserService {
	@Autowired
	private AbstractUserRepository abstractUserRepository;

	public void add(AbstractUser user) {
		abstractUserRepository.save(user);
	}
	
	public boolean isUserNameAlreadyExisted(String userName) {
		return abstractUserRepository.isUserNameAlreadyExisted(userName);
	}
}
