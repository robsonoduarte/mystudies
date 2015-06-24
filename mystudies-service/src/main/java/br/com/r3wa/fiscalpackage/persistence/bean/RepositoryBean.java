package br.com.r3wa.fiscalpackage.persistence.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.mystudies.domain.entity.Entity;
import br.com.r3wa.fiscalpackage.persistence.Repository;
import br.com.r3wa.fiscalpackage.persistence.ResultData;
import br.com.r3wa.fiscalpackage.persistence.exception.FiscalPacakagePersistenceRuntimeException;
import br.com.r3wa.fiscalpackge.persistence.query.JPAQueryRepository;



@SuppressWarnings("unchecked")
@Local(Repository.class)
@Stateless
public class RepositoryBean implements Repository {


	@PersistenceContext
	private EntityManager entityManager;


	@Inject
	private JPAQueryRepository queryRepository;



	@Override
	public <T extends Entity>  T save(T t) {
		return entityManager.merge(t);
	}



	@Override
	public <T> void remove(T t) {
		t = entityManager.merge(t);
		if(t == null){
			throw new FiscalPacakagePersistenceRuntimeException("Entidade não identificada");
		}
		entityManager.remove(t);

	}



	@Override
	public <T extends Entity> T removeAndSave(T t) {
		remove(t);
		entityManager.flush();
		entityManager.persist(t);
		return t;
	}



	@Override
	public <T extends Entity> T find(Class<T> entityClass, Object code) {
		return entityManager.find(entityClass , code);
	}



	@Override
	public <T extends Entity> List<T> select(String queryName, Object... parameters) {
		return createQuery(queryName, parameters).getResultList();
	}



	@Override
	public <T extends Entity> List<T> selectIN(String queryName, List<? extends Object> listParameters) {
		return  createQuery(queryName)
					.setParameter(1, listParameters)
					.getResultList();
	}



	@Override
	public <T extends Entity> List<T> selectIN(String queryName, int maxResult, List<? extends Object> listParameters) {
		return createQuery(queryName)
				.setParameter(1, listParameters)
				.setMaxResults(maxResult)
				.getResultList();
	}





	@Override
	public <T extends Entity> T selectOne(String queryName, Object... parameters) {
		try{
			return (T) createQuery(queryName, parameters).getSingleResult();
		}catch(NoResultException nr){
			return null;
		}
	}




	@Override
	public <T extends Entity> List<T> selectBetween(String queryName, Object start, Object end) {
		return createQuery(queryName)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		}



	@Override
	public <T extends ResultData> List<T> selectAtributes(String queryName, Object... parameters) {
		return createQuery(queryName, parameters).getResultList();
	}



































	// ----------------->>>> METODOS AUXILIARES <------------------------

	private Query createQuery(String queryName, Object... parameters) {
		Query query = entityManager.createQuery(queryRepository.getQuery(queryName));
		for (int i = 0; i < parameters.length; i++) {
			query.setParameter(i + 1, parameters[i]);
		}
		return query;
	}














}
