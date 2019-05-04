package edu.hcmuaf.tms.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.hcmuaf.tms.entity.AbstractUser;

@Repository
public class UserCredentialDAO {

	@Autowired
	private EntityManager entityManager;

	public AbstractUser findUserAccount(String userName) {
		try {
			String sql = "Select e from " + AbstractUser.class.getName() + " e " //
					+ " Where e.userName = :userName ";

			Query query = entityManager.createQuery(sql, AbstractUser.class);
			query.setParameter("userName", userName);

			return (AbstractUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
