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



@Stateless
@Local(Repository.class)
public class RepositoryBean implements Repository {


	@PersistenceContext
	private EntityManager entityManager;


	@Inject
	private QueryRepositoryFile queryRepository;



	@Override
	public <T extends Entity>  T save(T t) {
		return entityManager.merge(t);
	}


	@Override
	public Integer remove(String queryName, Long id) {
		Query query = createQuery(queryName);
		query.setParameter(1, id);
		return query.executeUpdate();
	}





	@Override
	public <T extends Entity> T find(Class<T> entityClass, Long id) {
		return entityManager.find(entityClass , id);
	}



	@Override
	public <T extends Entity> List<T> select(String queryName, Object... parameters) {
		Query query = createQuery(queryName);

		for (Object parameter : parameters) {
			query.setParameter(indexOf(parameters, parameter) + 1, parameter);
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
