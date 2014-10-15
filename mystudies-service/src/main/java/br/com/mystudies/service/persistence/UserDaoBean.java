package br.com.mystudies.service.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mystudies.domain.entity.User;

public class UserDaoBean implements UserDao {

	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public User findUserByLogin(User user) {
	
		entityManager.createQuery("select u from user u where u.email =  ");
		
		
		return null;
	}

	
}
