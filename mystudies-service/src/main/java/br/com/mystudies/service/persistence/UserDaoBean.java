package br.com.mystudies.service.persistence;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.mystudies.domain.entity.User;

@Stateless
@Local(UserDao.class)
public class UserDaoBean implements UserDao {



	@PersistenceContext
	private EntityManager entityManager;



	@Override
	public User findUserByLogin(User user) {

		Query query =
				entityManager.createQuery("select u from User u where u.email = :email and u.password = :password");

		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());

		return (User) query.getSingleResult();
	}


}
