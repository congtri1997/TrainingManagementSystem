package edu.hcmuaf.tms.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.hcmuaf.tms.entity.AbstractUser;

@Repository

public class RoleDAO {
	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(Long userId) {
		AbstractUser u = entityManager.find(AbstractUser.class, userId);
		return u.getRoles().stream().map(obj -> obj.getName()).collect(Collectors.toList());
	}

}
