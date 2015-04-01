package br.com.mystudies.service.persistence.bean;


import static org.apache.commons.lang3.ArrayUtils.indexOf;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.mystudies.domain.entity.Entity;
import br.com.mystudies.service.persistence.Repository;





@Local(Repository.class)
@Stateless
public class RepositoryBean implements Repository {


	@PersistenceContext
	private EntityManager entityManager;


	@Inject
	private QueryRepositoryFile queryRepository;



	@Override
	public <T extends Entity>  T save(T t) {
		entityManager.merge(t);
		return t;
	}


	@Override
	public Integer remove(String queryName, String code) {
		Query query = createQuery(queryName);
		query.setParameter(1, code);
		return query.executeUpdate();
	}





	@Override
	public <T extends Entity> T find(Class<T> entityClass, String code) {
		return entityManager.find(entityClass , code);
	}



	@Override
	public <T extends Entity> List<T> select(String queryName, Object... parameters) {
		Query query = createQuery(queryName);

		for (Object parameter : parameters) {
			query.setParameter(indexOf(parameters, parameter), parameter);
		}

		return query.getResultList(); //FIXME: verificar a anotações para suprir esse aviso
	}





	@Override
	public <T extends Entity> List<T> selectIN(String queryName, List<? extends Object> listParameters) {
		Query query = createQuery(queryName);
		query.setParameter(1, listParameters);
		return query.getResultList();
	}


	private Query createQuery(String queryName) {
		return entityManager.createQuery(queryRepository.getQuery(queryName));
	}



}
